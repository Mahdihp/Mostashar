package ir.mostashar.model.client.controller.v1;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.dto.FileForm;
import ir.mostashar.model.client.dto.FileUpdateForm;
import ir.mostashar.model.client.service.UserServiceImpl;
import ir.mostashar.model.file.dto.FileDTO;
import ir.mostashar.model.file.dto.ListFileDTO;
import ir.mostashar.model.file.service.FileService;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.repository.LawyerRepository;
import ir.mostashar.model.pack.dto.ListPackDTO;
import ir.mostashar.model.pack.dto.PackForm;
import ir.mostashar.model.pack.service.PackService;
import ir.mostashar.model.request.dto.ListRequestDTO;
import ir.mostashar.model.request.dto.RequestDTO;
import ir.mostashar.model.request.dto.RequestForm;
import ir.mostashar.model.request.service.RequestService;
import ir.mostashar.util.Constants;
import ir.mostashar.util.DataUtil;
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

    @Autowired
    PackService packService;

    @Autowired
    LawyerRepository lawyerRepository;

    @Autowired
    RequestService requestService;

    @PostMapping(value = "/createfile", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> createFile(@Valid @RequestBody FileForm fileForm) {
        Optional<Client> client = userService.findByClientId(UUID.fromString(fileForm.getUserId()));
        if (client.isPresent()) {
            if (fileService.existTitleFile(fileForm.getTitle(), client.get())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ListFileDTO("400", Constants.KEY_DUPLICATE_FILE));
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FileDTO("400", Constants.KEY_USER_NOT_FOUND));
        }

        UUID file = fileService.createFile(fileForm);
        if (file != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new FileDTO("200", Constants.KEY_CREATE_FILE_SUCSSES, file.toString()));
        }
        return null;
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ListFileDTO("500", Constants.KEY_CREATE_FILE_FAILED));
    }

    @PostMapping(value = "/removefile/{fileId}", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> removeFile(@PathVariable(value = "fileId") String fileId) {
        Optional<FileDTO> file = fileService.findFileByUid(fileId);
        if (file.isPresent()) {
            if (fileService.deleteFileByUid(fileId)) {
                return ResponseEntity.status(HttpStatus.OK).body(new ListFileDTO("200", Constants.KEY_DELETE_FILE));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ListFileDTO("404", Constants.KEY_NOT_FOUND_FILE));
        }
        return null;
    }

    @PostMapping(value = "/updatefile", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> updateFile(@Valid @RequestBody FileUpdateForm fileUpdateForm) {
        if (fileService.updateFile(fileUpdateForm)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ListFileDTO("200", Constants.KEY_UPDATE_FILE));
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ListFileDTO("404", Constants.KEY_NOT_FOUND_FILE));
    }

    @PostMapping(value = "/file/{userId}/{fileId}", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> findFileByClient(@PathVariable(value = "userId") String userId, @PathVariable(value = "fileId") String fileId) {
        Optional<FileDTO> file = fileService.findFileByUid(fileId);
        if (file.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(file.get());
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ListFileDTO("404", Constants.KEY_NOT_FOUND_FILE));
    }

    @PostMapping(value = "/files/{userid}", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> findAllFileByClient(@PathVariable(value = "userid") String userid) {
        Optional<ListFileDTO> allFileByUserId = fileService.findAllFileByUserId(userid);
        if (allFileByUserId.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(allFileByUserId.get());
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ListFileDTO("404", Constants.KEY_NOT_FOUND_FILE));
    }

    @GetMapping(value = "/packs/{lawyerid}", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> findAllPackByLawyer(@PathVariable(value = "lawyerid") String lawyerid) {
        if (!DataUtil.isValidUUID(lawyerid))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ListFileDTO("404", Constants.KEY_UUID_NOT_VALID));

        Optional<Lawyer> lawyer = lawyerRepository.findByUid(UUID.fromString(lawyerid));
        if (lawyer.isPresent()) {
            Optional<ListPackDTO> allPacks = packService.findAllPacks(lawyer.get().getPricePerMinute());
            if (allPacks.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(allPacks.get());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ListFileDTO("404", Constants.KEY_NOT_FOUND_LAWYER));
    }

    @PostMapping(value = "/buypack", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> buyPack(@Valid @RequestBody PackForm packForm) {
        return null;
    }

    @PostMapping(value = "/createrequest", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> createRequest(@Valid @RequestBody RequestForm requestForm) {
        if (!requestService.existsRequest(requestForm.getFileId(),requestForm.getClientId())){
            UUID requestId = requestService.createRequest(requestForm);
            if (requestId != null) {
                return ResponseEntity.status(HttpStatus.OK).body(new RequestDTO("200", Constants.KEY_CREATE_REQUEST_SUCSSES, requestId.toString()));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestDTO("404", Constants.KEY_NOT_FOUND_CLIENT_LAWYER_FILE));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestDTO("404", Constants.KEY_DUPLICATE_REQUEST));
    }

    @GetMapping(value = "/request/{clientid}/{requestid}", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> findRequestByClient(@PathVariable(value = "clientid") String clientid, @PathVariable(value = "requestid") String requestid) {
        Optional<RequestDTO> request = requestService.findRequestByClient(clientid, requestid);
        if (request.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(request.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestDTO("404", Constants.KEY_NOT_FOUND_REQUEST));
    }

    @GetMapping(value = "/requests/{clientid}", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> findAllRequestByClient(@PathVariable(value = "clientid") String clientid) {
        Optional<ListRequestDTO> allRequestClient = requestService.findAllRequestClient(clientid);
        if (allRequestClient.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(allRequestClient.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestDTO("404", Constants.KEY_NOT_FOUND_REQUEST));
    }

    @PostMapping(value = "/removerequest/{clientid}/{requestid}", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> removeRequest(@PathVariable(value = "clientid") String clientid, @PathVariable(value = "requestid") String requestid) {
        if (requestService.deleteRequest(clientid, requestid)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestDTO("200", Constants.KEY_DELETE_REQUEST));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestDTO("404", Constants.KEY_NOT_FOUND_REQUEST));
    }

}
