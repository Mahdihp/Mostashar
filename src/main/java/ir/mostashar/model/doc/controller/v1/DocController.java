package ir.mostashar.model.doc.controller.v1;


import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.activity.dto.LawyerActivityForm;
import ir.mostashar.model.activity.service.LawyerActivityService;
import ir.mostashar.model.doc.Doc;
import ir.mostashar.model.doc.dto.DocDTO;
import ir.mostashar.model.doc.dto.ListDocDTO;
import ir.mostashar.model.doc.service.DocService;
import ir.mostashar.model.file.File;
import ir.mostashar.model.file.dto.FileDTO;
import ir.mostashar.model.file.dto.ListFileDTO;
import ir.mostashar.model.file.service.FileService;
import ir.mostashar.model.request.Request;
import ir.mostashar.model.request.service.RequestService;
import ir.mostashar.utils.Constants;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/docs")
public class DocController {

    @Autowired
    DocService docService;

    @Autowired
    FileService fileService;

    @Autowired
    LawyerActivityService laService;

    @Autowired
    RequestService requestService;

    @ApiOperation(value = "Create Document Client", notes = "doctype : 0=Audio, 1=Video, 2=PDF, 3=Picture, 4=Text, 5=ZipFile, 6=RARFile" + "\n" + "RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Transactional
    @PostMapping(value = "/createdoc", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createDocument(@RequestParam("file") MultipartFile file, @RequestParam(value = "fileid") String fileUid, @RequestParam(value = "doctype") int docType, @RequestParam(value = "lawyerid") String lawyerUid) {
        Optional<File> fileByUid = fileService.findFileByUid(fileUid);
        if (fileByUid.isPresent()) {
            UUID docUid = docService.createDoc(fileByUid.get(), docType, file);
            if (docUid != null) {
                if (!TextUtils.isEmpty(lawyerUid)) {
                    LawyerActivityForm laForm = new LawyerActivityForm();
                    laForm.setLawyerId(lawyerUid);
                    laForm.setTitle(Constants.KEYT_CREATE_DOC_SUCSSES);
                    laForm.setDescription(Constants.KEY_ADD_DOC_FROM_LAWYER);
                    laForm.setFileId(fileUid);
                    laForm.setDocid(docUid.toString());
                    laForm.setType(docType);
                    laService.createLawyerActivity(laForm);
                }
                return ResponseEntity.status(HttpStatus.OK).body(new DocDTO(HttpStatus.OK.value(), Constants.KEYT_CREATE_DOC_SUCSSES, docUid.toString()));
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(new FileDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_FILE));
    }

    @ApiOperation(value = "Find One Document Without Data", notes = "RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/doc", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findDocByUid(@RequestParam("docId") String docId, @RequestParam("userid") String userid, @RequestParam("fileid") String fileId) {
        Optional<DocDTO> doc = docService.findByWithoutDataUid(docId, userid, fileId);
        if (doc.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(doc.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new FileDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_DOC));
    }

    @ApiOperation(value = "Find All Document Without Data", notes = "RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/docs", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllDocByUid(@RequestParam("userid") String userid, @RequestParam("fileid") String fileid) {
        Optional<ListDocDTO> docs = docService.findAllByWithoutDataUid(userid, fileid);
        if (docs.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(docs.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new FileDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_DOC));
    }


    @PostMapping(value = "/resumedocs", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllDocBylawyerid(@RequestParam("lawyerid") String lawyerid) {
        Optional<ListDocDTO> docs = docService.findAllresumeBylawyerid(lawyerid);
        if (docs.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(docs.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new FileDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_DOC));
    }

    @ApiOperation(value = "Download Data Document", notes = "RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Transactional
    @PostMapping(value = "/docdata", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> findDocDataByUid(@RequestParam("docId") String docUid, @RequestParam("userid") String userid, @RequestParam("fileid") String fileUid, @RequestParam(value = "lawyerid") String lawyerUid) {
        Optional<Doc> doc = docService.findByUid(docUid, userid, fileUid);
        if (doc.isPresent()) {
            if (!TextUtils.isEmpty(lawyerUid)) {
                LawyerActivityForm laForm = new LawyerActivityForm();
                laForm.setLawyerId(lawyerUid);
                laForm.setTitle(Constants.KEY_DOWNLOAD_DOC);
                laForm.setDescription(Constants.KEY_DOWNLAOD_DOC_FROM_LAWYER);
                laForm.setFileId(fileUid);
                laForm.setDocid(docUid);
                laForm.setType(doc.get().getDocType().type);
                laService.createLawyerActivity(laForm);
            }
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
            headers.add("Content-Length", String.valueOf(doc.get().getData().length));

            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(docService.findDocDataByUid(doc));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new FileDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_DOC));
    }

    @ApiOperation(value = "Delete Document", notes = "RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/removedoc", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> removeDocByUid(@RequestParam("docId") String docUid, @RequestParam("userid") String userUid, @RequestParam("fileid") String fileUid, @RequestParam(value = "lawyerid") String lawyerUid) {
        Optional<Request> request = requestService.findByFileUid(fileUid);
        if (request.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(new ListFileDTO(HttpStatus.OK.value(), Constants.KEY_NOT_UPDATE));

        if (docService.deleteDoc(docUid, userUid, fileUid)) {
            if (!TextUtils.isEmpty(lawyerUid)) {
                LawyerActivityForm laForm = new LawyerActivityForm();
                laForm.setLawyerId(lawyerUid);
                laForm.setTitle(Constants.KEY_DOWNLOAD_DOC);
                laForm.setDescription(Constants.KEY_DOWNLAOD_DOC_FROM_LAWYER);
                laForm.setFileId(fileUid);
                laForm.setDocid(docUid);
                laForm.setType(13);
                laService.createLawyerActivity(laForm);
            }
            return ResponseEntity.status(HttpStatus.OK).body(new FileDTO(HttpStatus.OK.value(), Constants.KEY_DELETE_DOC));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new FileDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_DOC));
    }
}
