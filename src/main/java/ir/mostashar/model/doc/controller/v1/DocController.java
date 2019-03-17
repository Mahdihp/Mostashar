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
import ir.mostashar.model.file.service.FileService;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.service.LawyerService;
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
    private DocService docService;
    @Autowired
    private FileService fileService;
    @Autowired
    private LawyerActivityService laService;
    @Autowired
    private LawyerService lawyerService;


    @ApiOperation(value = "Create Document Client", notes = "mimeType : 0=Audio, 1=Video, 2=PDF, 3=Picture, 4=Text, 5=ZipFile, 6=RARFile" + "\n" + "doctype 0=file & 1=resume" + "\n" + "RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Transactional
    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createDocument(@RequestParam("file") MultipartFile file,
                                            @RequestParam("fileid") String fileUid,
                                            @RequestParam("doctype") int docType,
                                            @RequestParam("mimetype") int mimeType,
                                            @RequestParam("lawyerid") String lawyerid) {
        Optional<File> fileByUid = fileService.findFileByUid(fileUid);

        if (fileByUid.isPresent()) {
            UUID docUid = docService.create(fileByUid.get(), mimeType, docType, file);
            if (docUid != null) {
                if (!TextUtils.isEmpty(lawyerid)) {
                    LawyerActivityForm laForm = new LawyerActivityForm();
                    laForm.setLawyerId(lawyerid);
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
    public ResponseEntity<?> findDocByUid(@RequestParam("docid") String docId,
                                          @RequestParam("doctype") int docType,
                                          @RequestParam("fileid") String fileId,
                                          @RequestParam("lawyerid") String lawyerId) {

        if (docType == 0) { // File
            Optional<DocDTO> doc = docService.findByIdWithoutData(docId, fileId);
            if (doc.isPresent())
                return ResponseEntity.status(HttpStatus.OK).body(doc.get());
            else
                return ResponseEntity.status(HttpStatus.OK).body(new FileDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_DOC));
        } else {
            Optional<Doc> doc = docService.findByLawyerId(lawyerId, docId);
            if (doc.isPresent())
                return ResponseEntity.status(HttpStatus.OK).body(doc.get());
            else
                return ResponseEntity.status(HttpStatus.OK).body(new FileDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_DOC));
        }
    }

    @ApiOperation(value = "Find All Document Without Data", notes = "RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/docs", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllDocByUid(@RequestParam("clientid") String clientid,
                                             @RequestParam("fileid") String fileid,
                                             @RequestParam("doctype") int docType,
                                             @RequestParam("lawyerid") String lawyerid) {
        if (docType == 0) { // File
            Optional<ListDocDTO> docs = docService.findAllByIdWithoutData(clientid, fileid);
            if (docs.isPresent())
                return ResponseEntity.status(HttpStatus.OK).body(docs.get());
            else
                return ResponseEntity.status(HttpStatus.OK).body(new FileDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_DOC));

        } else {
            Optional<ListDocDTO> list = docService.findAllResumeByLawyerId(lawyerid);
            if (list.isPresent())
                return ResponseEntity.status(HttpStatus.OK).body(list.get());
            else
                return ResponseEntity.status(HttpStatus.OK).body(new FileDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_DOC));
        }
    }


    @PostMapping(value = "/resumedocs", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllDocBylawyerid(@RequestParam("lawyerid") String lawyerid) {
        Optional<ListDocDTO> docs = docService.findAllResumeByLawyerId(lawyerid);
        if (docs.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(docs.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new FileDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_DOC));
    }

    @ApiOperation(value = "Download Data Document", notes = "RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Transactional
    @PostMapping(value = "/docdata", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> findByIdData(@RequestParam("docid") String docId,
                                          @RequestParam("doctype") int docType,
                                          @RequestParam("fileid") String fileId,
                                          @RequestParam("lawyerid") String lawyerId) {
        if (docType == 0) { // File
            Optional<Doc> doc = docService.findById(docId, fileId);
            if (doc.isPresent()) {
                if (!TextUtils.isEmpty(lawyerId)) {
                    LawyerActivityForm laForm = new LawyerActivityForm();
                    laForm.setLawyerId(lawyerId);
                    laForm.setTitle(Constants.KEY_DOWNLOAD_DOC);
                    laForm.setDescription(Constants.KEY_DOWNLAOD_DOC_FROM_LAWYER);
                    laForm.setFileId(fileId);
                    laForm.setDocid(docId);
                    laForm.setType(doc.get().getDocType().type);
                    laService.createLawyerActivity(laForm);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
                headers.add("Content-Length", String.valueOf(doc.get().getData().length));
                return ResponseEntity.status(HttpStatus.OK).headers(headers).body(docService.findDocDataByUid(doc));
            }
        } else {
            Optional<Lawyer> lawyer = lawyerService.findByUid(lawyerId);
            if (lawyer.isPresent()) {
                Optional<Doc> doc = docService.findByLawyerId(lawyerId, docId);
                if (doc.isPresent()) {
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
                    headers.add("Content-Length", String.valueOf(doc.get().getData().length));
                    return ResponseEntity.status(HttpStatus.OK).headers(headers).body(docService.findDocDataByUid(doc));
                }
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(new FileDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_DOC));
    }

    @ApiOperation(value = "Delete Document", notes = "RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/removedoc", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> removeDoc(@RequestParam("docid") String docId,
                                       @RequestParam("doctype") int docType,
                                       @RequestParam("fileid") String fileId,
                                       @RequestParam("lawyerid") String lawyerId) {

        if (docType == 0) { // File
            if (docService.deleteDoc(docId, fileId))
                return ResponseEntity.status(HttpStatus.OK).body(new FileDTO(HttpStatus.OK.value(), Constants.KEY_DELETE_DOC));

        } else {
            Optional<Lawyer> lawyer = lawyerService.findByUid(lawyerId);
            if (!lawyer.isPresent())
                return ResponseEntity.status(HttpStatus.OK).body(new FileDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_DOC));
            if (docService.deleteLawyerDoc(docId, lawyerId)) {
                if (!TextUtils.isEmpty(lawyerId)) {
                    LawyerActivityForm laForm = new LawyerActivityForm();
                    laForm.setLawyerId(lawyerId);
                    laForm.setTitle(Constants.KEY_DOWNLOAD_DOC);
                    laForm.setDescription(Constants.KEY_DOWNLAOD_DOC_FROM_LAWYER);
                    laForm.setFileId(fileId);
                    laForm.setDocid(docId);
                    laForm.setType(13);
                    laService.createLawyerActivity(laForm);
                }
                return ResponseEntity.status(HttpStatus.OK).body(new FileDTO(HttpStatus.OK.value(), Constants.KEY_DELETE_DOC));
            }

        }
        return ResponseEntity.status(HttpStatus.OK).body(new FileDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_DOC));
    }
}
