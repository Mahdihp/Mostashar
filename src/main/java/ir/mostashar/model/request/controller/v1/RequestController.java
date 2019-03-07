package ir.mostashar.model.request.controller.v1;

import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.acceptRequest.service.AcceptRequestService;
import ir.mostashar.model.lawyer.dto.LawyerDTO;
import ir.mostashar.model.notification.dto.NotificationForm;
import ir.mostashar.model.notification.service.NotificationService;
import ir.mostashar.model.request.Request;
import ir.mostashar.model.request.RequestStatus;
import ir.mostashar.model.request.dto.ListRequestDTO;
import ir.mostashar.model.request.dto.RequestDTO;
import ir.mostashar.model.request.dto.RequestForm;
import ir.mostashar.model.request.service.RequestService;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/requests")
public class RequestController {

    @Autowired
    RequestService requestService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    AcceptRequestService arService;

    @ApiOperation(value = "Create Request", notes ="RequestBody :" + MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Transactional
    @PostMapping(value = "/createrequest", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createRequest(@Valid @RequestBody RequestForm requestForm) {
        UUID requestId = requestService.createRequest(requestForm);
        if (requestId != null) {
            String content = Constants.KEY_NOTIFY_CREATE_REQUEST + "\n";
            content += " شماره پرونده: " + requestForm.getFileNumber() + "\n";
            content += " شماره درخواست: " + requestId.toString() + "\n";
            NotificationForm nForm = new NotificationForm(content, System.currentTimeMillis(), requestId.toString());

            System.out.println("Log---createRequest--------------------:" + nForm.getRequestId());
            notificationService.createNotification(nForm);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestDTO(HttpStatus.OK.value(), Constants.KEY_CREATE_REQUEST_SUCSSES, requestId.toString()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_FILE_USER));
        }
    }

    @ApiOperation(value = "Update Request", notes ="RequestBody :" + MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping(value = "/updaterequest", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> updateRequest(@Valid @RequestBody RequestForm requestForm) {
        if (requestService.updateRequest(requestForm)) {
            return ResponseEntity.status(HttpStatus.OK).body(new RequestDTO(HttpStatus.OK.value(), Constants.KEY_UPDATE_REQUEST_SUCSSES));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_CLIENT_LAWYER_FILE));
        }
    }

    @ApiOperation(value = "Find One Request", notes ="RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/request", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findRequestByClient(@RequestParam("clientId") String clientid, @RequestParam("requestid") String requestid) {
        Optional<RequestDTO> request = requestService.findRequestByClient(clientid, requestid);
        if (request.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(request.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_REQUEST));
    }

    @ApiOperation(value = "Find All Request", notes ="RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/requests", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllRequestByClient(@RequestParam("clientId") String clientid) {
        Optional<ListRequestDTO> allRequestClient = requestService.findAllRequestClient(clientid);
        if (allRequestClient.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(allRequestClient.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_REQUEST));
    }

    @ApiOperation(value = "Delete Request", notes ="RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/removerequest", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> removeRequest(@RequestParam("requestid") String requestid) {
        Optional<Request> request = requestService.findByUid(requestid);
        if (request.isPresent()) {
            if (requestService.deleteRequest(request.get())) {
                return ResponseEntity.status(HttpStatus.OK).body(new RequestDTO(HttpStatus.OK.value(), Constants.KEY_DELETE_REQUEST));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_REQUEST));
    }





}
