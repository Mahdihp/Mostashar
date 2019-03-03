package ir.mostashar.model.client.controller.v1;

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

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/client")
public class RequestController {

    @Autowired
    RequestService requestService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    AcceptRequestService arService;

    // ایا امکان ایجاد درخواست های مکرر و تکراری برای یه پرونده وجود دارد؟
    // چطوری جلو هک و ارسال درخواست تکرای را بگیریم
    @PostMapping(value = "/createrequest", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createRequest(@Valid @RequestBody RequestForm requestForm) {
        UUID requestId = requestService.createRequest(requestForm);
        if (requestId != null) {
            String content = Constants.KEY_NOTIFY_CREATE_REQUEST + "\n";
            content += " شماره پرونده: " + requestForm.getFileNumber() + "\n";
            content += " شماره درخواست: " + requestId.toString() + "\n";
            NotificationForm nForm = new NotificationForm(content, System.currentTimeMillis(), requestId.toString());

            System.out.println("Log---createRequest--------------------:" + nForm.toString());
            notificationService.createNotification(nForm);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestDTO(HttpStatus.OK.value(), Constants.KEY_CREATE_REQUEST_SUCSSES, requestId.toString()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_FILE_USER));
        }
    }

    @PostMapping(value = "/updaterequest", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> updateRequest(@Valid @RequestBody RequestForm requestForm) {
        if (requestService.updateRequest(requestForm)) {
            return ResponseEntity.status(HttpStatus.OK).body(new RequestDTO(HttpStatus.OK.value(), Constants.KEY_UPDATE_REQUEST_SUCSSES));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_CLIENT_LAWYER_FILE));
        }
    }

    @PostMapping(value = "/request", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findRequestByClient(@RequestParam("userId") String clientid, @RequestParam("requestid") String requestid) {
        Optional<RequestDTO> request = requestService.findRequestByClient(clientid, requestid);
        if (request.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(request.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_REQUEST));
    }

    @PostMapping(value = "/requests", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllRequestByClient(@RequestParam("userId") String clientid) {
        Optional<ListRequestDTO> allRequestClient = requestService.findAllRequestClient(clientid);
        if (allRequestClient.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(allRequestClient.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_REQUEST));
    }

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

    @PostMapping(value = "/assinglawyerrequest", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> acceptedLawyerByClient(@RequestParam("lawyerid") String lawyerId, @RequestParam("requestid") String requestId) {
        if (arService.assignLawyerToRequest(lawyerId, requestId, true)) {
            requestService.updateStatusRequest(requestId, RequestStatus.WAIT_PEYMENT);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestDTO(HttpStatus.OK.value(), Constants.KEY_ASSIGN_LAWYER_TO_REQUEST));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_REQUEST));
    }

    @PostMapping(value = "/rejectlawyerrequest", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> rejectedLawyerByClient(@RequestParam("lawyerid") String lawyerId, @RequestParam("requestid") String requestId) {
        if (arService.assignLawyerToRequest(lawyerId, requestId, false)) {
            requestService.updateStatusRequest(requestId, RequestStatus.SELECT_LAWYER);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestDTO(HttpStatus.OK.value(), Constants.KEY_REJECT_LAWYER_TO_REQUEST));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_REQUEST));
    }



}
