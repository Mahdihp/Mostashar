package ir.mostashar.model.client.controller.v1;

import ir.mostashar.model.client.service.UserServiceImpl;
import ir.mostashar.model.file.dto.ListFileDTO;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.repository.LawyerRepository;
import ir.mostashar.model.pack.dto.ListPackDTO;
import ir.mostashar.model.pack.dto.PackForm;
import ir.mostashar.model.pack.service.PackService;
import ir.mostashar.model.request.Request;
import ir.mostashar.model.request.dto.ListRequestDTO;
import ir.mostashar.model.request.dto.RequestDTO;
import ir.mostashar.model.request.dto.RequestForm;
import ir.mostashar.model.request.service.RequestService;
import ir.mostashar.util.Constants;
import ir.mostashar.util.DataUtil;
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
@RequestMapping("/api/v1/client")
public class RequestController {

    @Autowired
    RequestService requestService;

    @PostMapping(value = "/createrequest", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createRequest(@Valid @RequestBody RequestForm requestForm) {
        if (!requestService.existsRequest(requestForm.getFileId(), requestForm.getClientId())) {
            UUID requestId = requestService.createRequest(requestForm);
            if (requestId != null) {
                return ResponseEntity.status(HttpStatus.OK).body(new RequestDTO(HttpStatus.OK.value(), Constants.KEY_CREATE_REQUEST_SUCSSES, requestId.toString()));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_CLIENT_LAWYER_FILE));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_DUPLICATE_REQUEST));
    }

    @PostMapping(value = "/updaterequest", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> updateRequest(@Valid @RequestBody RequestForm requestForm) {
        if (!requestService.existsRequest(requestForm.getFileId(), requestForm.getClientId())) {
            if (requestService.updateRequest(requestForm)) {
                return ResponseEntity.status(HttpStatus.OK).body(new RequestDTO(HttpStatus.OK.value(), Constants.KEY_UPDATE_REQUEST_SUCSSES));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_CLIENT_LAWYER_FILE));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_DUPLICATE_REQUEST));
    }

    @GetMapping(value = "/request/{clientid}/{requestid}", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findRequestByClient(@PathVariable(value = "clientid") String clientid, @PathVariable(value = "requestid") String requestid) {
        Optional<RequestDTO> request = requestService.findRequestByClient(clientid, requestid);
        if (request.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(request.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_REQUEST));
    }

    @GetMapping(value = "/requests/{clientid}", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllRequestByClient(@PathVariable(value = "clientid") String clientid) {
        Optional<ListRequestDTO> allRequestClient = requestService.findAllRequestClient(clientid);
        if (allRequestClient.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(allRequestClient.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_REQUEST));
    }

    @PostMapping(value = "/removerequest/{clientid}/{requestid}", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> removeRequest(@PathVariable(value = "clientid") String clientid, @PathVariable(value = "requestid") String requestid) {
        Optional<Request> request = requestService.findByClientIdAndRequestId(clientid, requestid);
        if (request.isPresent()) {
            if (requestService.deleteRequest(request.get())) {
                return ResponseEntity.status(HttpStatus.OK).body(new RequestDTO(HttpStatus.OK.value(), Constants.KEY_DELETE_REQUEST));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_REQUEST));
    }

}