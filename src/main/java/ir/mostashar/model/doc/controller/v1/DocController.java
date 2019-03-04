package ir.mostashar.model.doc.controller.v1;


import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.doc.Doc;
import ir.mostashar.model.doc.dto.DocDTO;
import ir.mostashar.model.doc.dto.ListDocDTO;
import ir.mostashar.model.doc.service.DocService;
import ir.mostashar.model.file.File;
import ir.mostashar.model.file.dto.FileDTO;
import ir.mostashar.model.file.service.FileService;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/doc")
public class DocController {

    @Autowired
    DocService docService;

    @Autowired
    FileService fileService;

    @ApiOperation(value = "Create Document Client", notes = "doctype : 0=Audio, 1=Video, 2=PDF, 3=Picture, 4=Text, 5=ZipFile, 6=RARFile"+"\n"+ "RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/createdoc", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createDocument(@RequestParam("file") MultipartFile file, @RequestParam(value = "fileid") String fileid, @RequestParam(value = "doctype") int docType) {
        Optional<File> fileByUid = fileService.findFileByUid(fileid);
        if (fileByUid.isPresent()) {
            UUID doc = docService.createDoc(fileByUid.get(), docType, file);
            if (doc != null)
                return ResponseEntity.status(HttpStatus.OK).body(new DocDTO(HttpStatus.OK.value(), Constants.KEYT_CREATE_DOC_SUCSSES, doc.toString()));
        }
        return null;
    }
    @ApiOperation(value = "Find One Document Without Data", notes ="RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/doc", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findDocByUid(@RequestParam("docId") String docId, @RequestParam("userid") String userid, @RequestParam("fileid") String fileId) {
        Optional<DocDTO> doc = docService.findByWithoutDataUid(docId,userid,fileId);
        if (doc.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(doc.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new FileDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_DOC));
    }

    @ApiOperation(value = "Find All Document Without Data", notes ="RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/docs", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllDocByUid(@RequestParam("userid") String userid, @RequestParam("fileid") String fileid) {
        Optional<ListDocDTO> docs = docService.findAllByWithoutDataUid(userid, fileid);
        if (docs.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(docs.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new FileDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_DOC));
    }

    @ApiOperation(value = "Download Data Document", notes ="RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/docdata", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> findDocDataByUid(@RequestParam("docId") String docId, @RequestParam("userid") String userid, @RequestParam("fileid") String fileId) {
        Optional<Doc> doc = docService.findByUid(docId,userid,fileId);
        if (doc.isPresent()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
            headers.add("Content-Length", String.valueOf(doc.get().getData().length));

            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(docService.findDocDataByUid(doc));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new FileDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_DOC));
    }

    @ApiOperation(value = "Delete Document", notes ="RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/removedoc", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> removeDocByUid(@RequestParam("docId") String docId, @RequestParam("userid") String userid, @RequestParam("fileid") String fileId) {
        if (docService.deleteDoc(docId,userid,fileId))
            return ResponseEntity.status(HttpStatus.OK).body(new FileDTO(HttpStatus.OK.value(), Constants.KEY_DELETE_DOC));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new FileDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_DOC));
    }
}
