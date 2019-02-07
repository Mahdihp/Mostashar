package ir.mostashar.model.client.controller.v1;

import ir.mostashar.model.client.dto.FileForm;
import ir.mostashar.model.client.dto.FileUpdateForm;
import ir.mostashar.model.client.service.UserServiceImpl;
import ir.mostashar.model.file.File;
import ir.mostashar.model.file.dto.FileDTO;
import ir.mostashar.model.file.repository.FileRepository;
import ir.mostashar.model.file.service.FileService;
import ir.mostashar.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    @Autowired
    FileRepository fileRepository;

    @Autowired
    FileService fileService;

    @PostMapping(value = "/createFile", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> createFile(@Valid @RequestBody FileForm fileForm) {
        if (fileService.checkExistTitleFile(fileForm.getTitle())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FileDTO("23", Constants.KEY_DUPLICATE_FILE, ""));
        }
        UUID file = fileService.createFile(fileForm);
        if (file != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new FileDTO("200", Constants.KEY_CREATE_FILE_SUCSSES, file.toString()));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new FileDTO("500", Constants.KEY_CREATE_FILE_FAILED, ""));
    }

    @PostMapping(value = "/removeFile/{fileId}", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> removeFile(@PathVariable(value = "fileId") String fileId) {
        return null;
    }

    @PostMapping(value = "/updateFile", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> updateFile(@Valid @RequestBody FileUpdateForm fileUpdateForm) {
        return null;
    }

    @PostMapping(value = "/file/{fileId}", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> findFileById(@PathVariable(value = "fileId") String fileId){
        return null;
    }

    @PostMapping(value = "/files/{userid}", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> findAllFileByUserId(@PathVariable(value = "userid") String userid){
        return null;
    }




}
