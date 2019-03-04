package ir.mostashar.model.file.controller.v1;

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
@RequestMapping("/api/v1/file")
public class FileController {

    @Autowired
    FileService fileService;

    @Autowired
    UserServiceImpl userService;

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




}
