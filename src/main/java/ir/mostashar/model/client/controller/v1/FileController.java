package ir.mostashar.model.client.controller.v1;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.dto.FileForm;
import ir.mostashar.model.client.dto.FileUpdateForm;
import ir.mostashar.model.client.service.UserServiceImpl;
import ir.mostashar.model.doc.Doc;
import ir.mostashar.model.doc.dto.DocDTO;
import ir.mostashar.model.doc.dto.DocForm;
import ir.mostashar.model.doc.dto.ListDocDTO;
import ir.mostashar.model.doc.service.DocService;
import ir.mostashar.model.file.File;
import ir.mostashar.model.file.dto.FileDTO;
import ir.mostashar.model.file.dto.ListFileDTO;
import ir.mostashar.model.file.service.FileService;
import ir.mostashar.util.Constants;
import ir.mostashar.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @PostMapping(value = "/createfile", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createFile(@Valid @RequestBody FileForm fileForm) {
        Optional<Client> client = userService.findByClientId(UUID.fromString(fileForm.getUserId()));
        if (client.isPresent()) {
            if (fileService.existTitleFile(fileForm.getTitle(), client.get())) {
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

    @PostMapping(value = "/removefile/{fileId}", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> removeFile(@PathVariable(value = "fileId") String fileId) {
        Optional<FileDTO> file = fileService.findFileDTOByUid(fileId);
        if (file.isPresent()) {
            if (fileService.deleteFileByUid(fileId)) {
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

    @PostMapping(value = "/file/{userId}/{fileId}", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findFileByClient(@PathVariable(value = "userId") String userId, @PathVariable(value = "fileId") String fileId) {
        Optional<FileDTO> file = fileService.findFileDTOByUid(fileId);
        if (file.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(file.get());
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ListFileDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_FILE));
    }

    @PostMapping(value = "/files/{userid}", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllFileByClient(@PathVariable(value = "userid") String userid) {
        Optional<ListFileDTO> allFileByUserId = fileService.findAllFileByUserId(userid);
        if (allFileByUserId.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(allFileByUserId.get());
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ListFileDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_FILE));
    }

    @PostMapping(value = "/createdoc", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createDocument(@RequestParam("file") MultipartFile file, @RequestParam(value = "fileid") String fileid, @RequestParam(value = "doctype") int doctype) {
        Optional<File> fileByUid = fileService.findFileByUid(fileid);
        if (fileByUid.isPresent()) {
            boolean isSaveDoc = docService.createDoc(fileByUid.get(), doctype, file);
            if (isSaveDoc)
                return ResponseEntity.status(HttpStatus.OK).body(new DocDTO(HttpStatus.OK.value(), Constants.KEYT_CREATE_DOC_SUCSSES));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new FileDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_FILE));
    }

    @GetMapping(value = "/doc/{docid}", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findDocByUid(@PathVariable(value = "docid") String docid) {
        Optional<DocDTO> doc = docService.findByWithoutDataUid(docid);
        if (doc.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(doc.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new FileDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_DOC));
    }

    @GetMapping(value = "/docs/{userid}/{fileid}", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllDocByUid(@PathVariable(value = "userid") String userid,@PathVariable(value = "fileid") String fileid) {
        Optional<ListDocDTO> docs = docService.findAllByWithoutDataUid(userid,fileid);
        if (docs.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(docs.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new FileDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_DOC));
    }

    @GetMapping(value = "/docdata/{docid}")
    public ResponseEntity<?> findDocDataByUid(@PathVariable(value = "docid") String docid) {
        Optional<Doc> doc = docService.findByUid(docid);
        if (doc.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(docService.findDocDataByUid(doc));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new FileDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_DOC));
    }

    @PostMapping(value = "removedoc", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> removeDocByUid(@PathVariable(value = "docid") String docid) {
        if (docService.deleteDoc(docid))
            return ResponseEntity.status(HttpStatus.OK).body(new FileDTO(HttpStatus.OK.value(), Constants.KEY_DELETE_DOC));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new FileDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_DOC));
    }


}
