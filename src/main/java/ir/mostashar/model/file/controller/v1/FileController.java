package ir.mostashar.model.file.controller.v1;

import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.BaseDTO;
import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.dto.FileForm;
import ir.mostashar.model.client.dto.FileUpdateForm;
import ir.mostashar.model.client.service.ClientService;
import ir.mostashar.model.feedback.service.FeedbackService;
import ir.mostashar.model.file.File;
import ir.mostashar.model.file.dto.FileDTO;
import ir.mostashar.model.file.dto.ListFileDTO;
import ir.mostashar.model.file.service.FileService;
import ir.mostashar.model.lawyer.service.LawyerService;
import ir.mostashar.model.request.Request;
import ir.mostashar.model.request.service.RequestService;
import ir.mostashar.model.user.User;
import ir.mostashar.model.user.service.UserServiceImpl;
import ir.mostashar.utils.Constants;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private LawyerService lawyerService;

    /**
     * First find client by clientId & exists file title & Later Create File Record
     *
     * @param fileForm
     * @return
     */
    @ApiOperation(value = "Create File Client", notes = "RequestBody :" + MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping(value = "/createfile", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createFile(@Valid @RequestBody FileForm fileForm) {
        Optional<Client> client = clientService.findClientByUidAndActive(fileForm.getUserId(), true);
        if (client.isPresent()) {
            if (fileService.existTitleFile(fileForm.getTitle(), client.get(), false)) {
                return ResponseEntity.status(HttpStatus.OK).body(new ListFileDTO(HttpStatus.OK.value(), Constants.KEY_DUPLICATE_FILE));
            }
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new FileDTO(HttpStatus.OK.value(), Constants.KEY_USER_NOT_FOUND));
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
    @ApiOperation(value = "Delete File Client", notes = "RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/removefile", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> removeFile(@RequestParam("fileid") String fileId) {
        Optional<File> file = fileService.findFileByUid(fileId);
        if (file.isPresent()) {
            if (fileService.deleteFileByUid(file.get())) {
                return ResponseEntity.status(HttpStatus.OK).body(new ListFileDTO(HttpStatus.OK.value(), Constants.KEY_DELETE_FILE));
            }
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ListFileDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_FILE));
        }
        return null;
    }

    @ApiOperation(value = "Update File Client", notes = "RequestBody :" + MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping(value = "/updatefile", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> updateFile(@Valid @RequestBody FileUpdateForm fileUpdateForm) {
        Optional<Request> request = requestService.findByFileUid(fileUpdateForm.getFileId());
        if (request.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(new ListFileDTO(HttpStatus.OK.value(), Constants.KEY_NOT_UPDATE));

        if (fileService.updateFile(fileUpdateForm)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ListFileDTO(HttpStatus.OK.value(), Constants.KEY_UPDATE_FILE));
        } else
            return ResponseEntity.status(HttpStatus.OK).body(new ListFileDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_FILE));
    }

    @ApiOperation(value = "Find One File By fileId", notes = "RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findFileByClient(@RequestParam("fileid") String fileId, @RequestParam("clientid") String clientId) {
        Optional<FileDTO> file = fileService.findDTOByUid(fileId, clientId);
        if (file.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(file.get());
        } else
            return ResponseEntity.status(HttpStatus.OK).body(new ListFileDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_FILE));
    }

    @ApiOperation(value = "Find All Files By Client & Lawyer", notes = "PathVariable(@Field) : userid")
    @PostMapping(value = "/{userid}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllFileByUsers(@PathVariable(value = "userid") String userId) {
        if (TextUtils.isEmpty(userId))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_FILE));

        Optional<User> user = userService.findUserByUid(userId);
        if (user.get() instanceof Client) {
            System.out.println("Log---findAllFileByUsers is Client--------------------");
            Optional<ListFileDTO> files = fileService.findAllFileByClientUid(userId);
            return ResponseEntity.status(HttpStatus.OK).body(files.get());
        } else {
            Optional<ListFileDTO> files = lawyerService.findAllFileLawyer(userId);
            return ResponseEntity.status(HttpStatus.OK).body(files.get());
        }
    }

    @PostMapping(value = "/requestfiles", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllFileByRequestId(@RequestParam("requestid") String requestid,
                                                    @RequestParam("clientid") String clientUid) {
        Optional<ListFileDTO> allFileByUserId = fileService.findAllFileByRequestId(requestid,clientUid);
        if (allFileByUserId.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(allFileByUserId.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new ListFileDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_FILE));
    }




}
