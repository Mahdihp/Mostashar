package ir.mostashar.model.client.controller.v1;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.dto.FileForm;
import ir.mostashar.model.client.dto.FileUpdateForm;
import ir.mostashar.model.client.service.UserServiceImpl;
import ir.mostashar.model.file.dto.FileDTO;
import ir.mostashar.model.file.service.FileService;
import ir.mostashar.model.pack.dto.PackForm;
import ir.mostashar.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/client")
public class ClientController {


    @Autowired
    FileService fileService;

    @Autowired
    UserServiceImpl userService;

    @PostMapping(value = "/createfile", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> createFile(@Valid @RequestBody FileForm fileForm) {
        Optional<Client> client = userService.findByClientId(UUID.fromString(fileForm.getUserId()));
        if (client.isPresent()) {
            if (fileService.checkExistTitleFile(fileForm.getTitle(), client.get())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FileDTO("23", Constants.KEY_DUPLICATE_FILE));
            }
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FileDTO("23", Constants.KEY_USER_NOT_FOUND));
        }

        UUID file = fileService.createFile(fileForm);
        if (file != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new FileDTO("200", Constants.KEY_CREATE_FILE_SUCSSES));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new FileDTO("500", Constants.KEY_CREATE_FILE_FAILED));
    }

    @PostMapping(value = "/removefile/{fileId}", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> removeFile(@PathVariable(value = "fileId") String fileId) {
        if (fileService.deleteFileByUid(fileId)) {
            return ResponseEntity.status(HttpStatus.OK).body(new FileDTO("200", Constants.KEY_DELETE_FILE));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new FileDTO("500", Constants.KEY_DELETE_ERROR_FILE));
    }

    @PostMapping(value = "/updatefile", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> updateFile(@Valid @RequestBody FileUpdateForm fileUpdateForm) {
        if (fileService.updateFile(fileUpdateForm)) {
            return ResponseEntity.status(HttpStatus.OK).body(new FileDTO("200", Constants.KEY_UPDATE_FILE));
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new FileDTO("404", Constants.KEY_NOT_FOUND_FILE));
    }

    @PostMapping(value = "/file/{fileId}", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> findFileById(@PathVariable(value = "fileId") String fileId) {
        Optional<FileDTO> file = fileService.findFileByUid(fileId);
        if (file.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(file.get());
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new FileDTO("404", Constants.KEY_NOT_FOUND_FILE));
    }

    @PostMapping(value = "/files/{userid}", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> findAllFileByUserId(@PathVariable(value = "userid") String userid) {
        Optional<FileDTO> allFileByUserId = fileService.findAllFileByUserId(userid);
        if (allFileByUserId.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(allFileByUserId.get());
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new FileDTO("404", Constants.KEY_NOT_FOUND_FILE));
    }

    @PostMapping(value = "/packages", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> findAllPackage() {
        return null;
    }

    @PostMapping(value = "/buypackage", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> buyPackages(@Valid @RequestBody PackForm packForm) {
        return null;
    }

}
