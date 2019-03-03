package ir.mostashar.model.client.controller.v1;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.dto.FileForm;
import ir.mostashar.model.client.dto.FileUpdateForm;
import ir.mostashar.model.client.service.ClientService;
import ir.mostashar.model.client.service.UserServiceImpl;
import ir.mostashar.model.doc.Doc;
import ir.mostashar.model.doc.dto.DocDTO;
import ir.mostashar.model.doc.dto.ListDocDTO;
import ir.mostashar.model.doc.service.DocService;
import ir.mostashar.model.file.File;
import ir.mostashar.model.file.dto.FileDTO;
import ir.mostashar.model.file.dto.ListFileDTO;
import ir.mostashar.model.file.service.FileService;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/client")
public class FileController {

    @Autowired
    FileService fileService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    DocService docService;

    @Autowired
    ClientService clientService;

    /**
     * First find client by userId & exists file title & Later Create File Record
     *
     * @param fileForm
     * @return
     */
    @PostMapping(value = "/createfile", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createFile(@Valid @RequestBody FileForm fileForm) {
        Optional<Client> client = clientService.findClientByUidAndActive(fileForm.getUserId(), true);
        if (client.isPresent()) {
            if (fileService.existTitleFile(fileForm.getTitle(), client.get(), false)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ListFileDTO(HttpStatus.BAD_REQUEST.value(), Constants.KEY_DUPLICATE_FILE));
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FileDTO(HttpStatus.BAD_REQUEST.value(), Constants.KEY_USER_NOT_FOUND));
        }

        UUID file = fileService.createFile(fileForm);
        if (file != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new FileDTO(HttpStatus.OK.value(), Constants.KEY_CREATE_FILE_SUCSSES, file.toString()));
        }
        return null;
    }

    /**
     * Find file Id and Later delete service file
     *
     * @param fileId
     * @return
     */
    @PostMapping(value = "/removefile", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> removeFile(@RequestParam("fileid") String fileId) {
        Optional<File> file = fileService.findFileByUid(fileId);
        if (file.isPresent()) {
            if (fileService.deleteFileByUid(file.get())) {
                return ResponseEntity.status(HttpStatus.OK).body(new ListFileDTO(HttpStatus.OK.value(), Constants.KEY_DELETE_FILE));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ListFileDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_FILE));
        }
        return null;
    }

    @PostMapping(value = "/updatefile", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> updateFile(@Valid @RequestBody FileUpdateForm fileUpdateForm) {
        if (fileService.updateFile(fileUpdateForm)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ListFileDTO(HttpStatus.OK.value(), Constants.KEY_UPDATE_FILE));
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ListFileDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_FILE));
    }

    @PostMapping(value = "/file", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findFileByClient(@RequestParam("fileid") String fileId) {
        Optional<FileDTO> file = fileService.findFileDTOByUid(fileId);
        if (file.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(file.get());
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ListFileDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_FILE));
    }

    @PostMapping(value = "/files", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllFileByClient(@RequestParam("userId") String userId) {
        Optional<ListFileDTO> allFileByUserId = fileService.findAllFileByUserId(userId);
        if (allFileByUserId.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(allFileByUserId.get());
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ListFileDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_FILE));
    }

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

    @PostMapping(value = "/doc", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findDocByUid(@RequestParam("docid") String docId, @RequestParam("userid") String userid, @RequestParam("fileid") String fileId) {
        Optional<DocDTO> doc = docService.findByWithoutDataUid(docId,userid,fileId);
        if (doc.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(doc.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new FileDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_DOC));
    }

    @PostMapping(value = "/docs", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllDocByUid(@RequestParam("userid") String userid, @RequestParam("fileid") String fileid) {
        Optional<ListDocDTO> docs = docService.findAllByWithoutDataUid(userid, fileid);
        if (docs.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(docs.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new FileDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_DOC));
    }

    @PostMapping(value = "/docdata", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> findDocDataByUid(@RequestParam("docid") String docId, @RequestParam("userid") String userid, @RequestParam("fileid") String fileId) {
        Optional<Doc> doc = docService.findByUid(docId,userid,fileId);
        if (doc.isPresent()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
            headers.add("Content-Length", String.valueOf(doc.get().getData().length));

            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(docService.findDocDataByUid(doc));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new FileDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_DOC));
    }

    @PostMapping(value = "/removedoc", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> removeDocByUid(@RequestParam("docid") String docId, @RequestParam("userid") String userid, @RequestParam("fileid") String fileId) {
        if (docService.deleteDoc(docId,userid,fileId))
            return ResponseEntity.status(HttpStatus.OK).body(new FileDTO(HttpStatus.OK.value(), Constants.KEY_DELETE_DOC));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new FileDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_DOC));
    }


}
