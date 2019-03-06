package ir.mostashar.model.lawyer.controller.v1;

import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.BaseDTO;
import ir.mostashar.model.acceptRequest.dto.AcceptRequestForm;
import ir.mostashar.model.acceptRequest.dto.ListAcceptRequestDTO;
import ir.mostashar.model.acceptRequest.service.AcceptRequestService;
import ir.mostashar.model.bill.dto.ListBillDTO;
import ir.mostashar.model.bill.service.BillService;
import ir.mostashar.model.failRequest.dto.FailRequestForm;
import ir.mostashar.model.failRequest.dto.ListFailRequestDTO;
import ir.mostashar.model.failRequest.service.FailRequestService;
import ir.mostashar.model.feedback.dto.FeedBackForm;
import ir.mostashar.model.feedback.service.FeedbackService;
import ir.mostashar.model.lawyer.dto.LawyerDTO;
import ir.mostashar.model.lawyer.dto.ListLawyerDTO;
import ir.mostashar.model.lawyer.service.LawyerService;
import ir.mostashar.model.notification.Notification;
import ir.mostashar.model.notification.service.NotificationService;
import ir.mostashar.model.reminder.service.ReminderService;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/lawyers")
public class LawyerController {


    @Autowired
    LawyerService lawyerService;

    @Autowired
    AcceptRequestService arService;

    @Autowired
    FailRequestService frService;

    @Autowired
    NotificationService nService;

    @Autowired
    ReminderService reminderService;

    @Autowired
    BillService billService;

    @Autowired
    FeedbackService feedbackService;

    @PostMapping(value = "/addfeedback", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> addFeedBack(@Valid @RequestBody FeedBackForm fbForm) {
        feedbackService.createFeedback(fbForm);
        return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_ONLINE));
    }

    /**
     * Find All Lawyer Online
     * @return
     */
    @PostMapping(value = "/onlines", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllLawyerOnline() {
        Optional<ListLawyerDTO> list = lawyerService.findListLawyerDTO(2, "", true);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_ONLINE));
    }

    /**
     * Set Online/Offline Status Lawyer
     * @param lawyerId
     * @param online
     * @return
     */
    @PostMapping(value = "/online", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> setOnlineLawyer(@RequestParam("lawyerid") String lawyerId, @RequestParam("online") boolean online) {
        if (lawyerService.setOnline(lawyerId, online))
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_SUCESSE));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_LAWYER));
    }

    /**
     * Set Read Notify From Lawyer
     * @param requestUid
     * @return
     */
    @PostMapping(value = "/readrequest", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> readRequest(@RequestParam("requestid") String requestUid) {
        Optional<Notification> notify = nService.findByRequestUid(requestUid);
        if (notify.isPresent()) {
            reminderService.setReadReminder(notify.get().getUid().toString());
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_READ_REQUEST));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_REQUEST));
    }

    /**
     * Add Into AcceptRequest Table
     * Set Read Notify From Lawyer
     * @param arForm
     * @return
     */
    @PostMapping(value = "/acceptrequest", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> acceptRequest(@Valid @RequestBody AcceptRequestForm arForm) {
        if (arService.createAcceptRequest(arForm)) {
            Optional<Notification> notify = nService.findByRequestUid(arForm.getRequestId());
            if (notify.isPresent()) {
                reminderService.setReadReminder(notify.get().getUid().toString());
                return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_ADD_ACCEPT_REQUEST));
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_REQUEST));
    }

    /**
     * Add Into FailRequest Table
     * @param frForm
     * @return
     */
    @PostMapping(value = "/failrequest", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> failRequest(@Valid @RequestBody FailRequestForm frForm) {
        if (frService.createFailRequest(frForm))
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_ADD_FAIL_REQUEST));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_REQUEST));
    }

    /**
     * Find All Accept Requests Lawyer
     * @param lawyerUid
     * @return
     */
    @PostMapping(value = "/accepts", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllAcceptRequest(@RequestParam("lawyerid") String lawyerUid) {
        Optional<ListAcceptRequestDTO> list = arService.findListAcceptRequestDTOByLawyer(lawyerUid);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_ACCEPTED));
    }

    /**
     * Find All Fail Requests Lawyer
     * @param lawyerUid
     * @return
     */
    @PostMapping(value = "/fails", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllFailRequest(@RequestParam("lawyerid") String lawyerUid) {
        Optional<ListFailRequestDTO> list = frService.findListFailRequestDTOByLawyer(lawyerUid);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_FAILS));
    }

    @PostMapping(value = "/bills", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllBillsLawyer(@RequestParam("lawyerid") String lawyerUid,@RequestParam("walletid") String walletUid) {
        Optional<ListBillDTO> list = billService.findListBillDTOByWalletUid(walletUid, lawyerUid);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_BILL));
    }

    @ApiOperation(value = "Delete Lawyer", notes ="RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/deletelawyer", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> deleteLawyer(@RequestParam("mobilenumber") String mobilenumber) {
        lawyerService.deleteLawyer(mobilenumber);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_DELETE_USER, false));
    }
}
