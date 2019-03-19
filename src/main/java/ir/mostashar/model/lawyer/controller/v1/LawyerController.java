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
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.dto.LawyerDTO;
import ir.mostashar.model.lawyer.dto.ListLawyerDTO;
import ir.mostashar.model.lawyer.service.LawyerService;
import ir.mostashar.model.notification.Notification;
import ir.mostashar.model.notification.service.NotificationService;
import ir.mostashar.model.reminder.service.ReminderService;
import ir.mostashar.model.request.service.RequestService;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/lawyers")
public class LawyerController {

    @Autowired
    private LawyerService lawyerService;
    @Autowired
    private AcceptRequestService arService;
    @Autowired
    private FailRequestService frService;
    @Autowired
    private NotificationService nService;
    @Autowired
    private ReminderService reminderService;
    @Autowired
    private BillService billService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private RequestService requestService;

    @PostMapping(value = "/addfeedback", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> addFeedBack(@Valid @RequestBody FeedBackForm fbForm) {
        if (!requestService.existsRequest(fbForm.getRequestId())) {
            feedbackService.createFeedback(fbForm);
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_SUCESSE_REGISTER));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_DUPLICATE_FEEDBACK));
    }

    /**
     * Find All Lawyer Online
     *
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
     *
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
     *
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
     *
     * @param arForm
     * @return
     */
    @PostMapping(value = "/acceptrequest", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> acceptRequest(@Valid @RequestBody AcceptRequestForm arForm) {
        if (arService.createByReading(arForm)) {
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
     *
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
     *
     * @param lawyerUid
     * @return
     */
    @PostMapping(value = "/accepts", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllAcceptRequest(@RequestParam("lawyerid") String lawyerUid) {
        Optional<ListAcceptRequestDTO> list = arService.findAllDTOByLawyer(lawyerUid);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_ACCEPTED));
    }

    /**
     * Find All Fail Requests Lawyer
     *
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
    public ResponseEntity<?> findAllBillsLawyer(@RequestParam("lawyerid") String lawyerUid,
                                                @RequestParam("walletid") String walletUid) {
        Optional<ListBillDTO> list = billService.findListBillDTOByWalletUid(walletUid, lawyerUid);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_BILL));
    }

    @PostMapping(value = "/findbyid", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findLawyerId(@RequestParam("lawyerid") String lawyerUid) {
        Optional<Lawyer> list = lawyerService.findByUid(lawyerUid);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_BILL));
    }


    @ApiOperation(value = "List Of Lawyer accept request or reading request", notes = "RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/arlawyer", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> addAcceptRequest(@RequestParam("requestid") String requestid,
                                              @RequestParam("fileid") String fileId) {
        Optional<ListLawyerDTO> list = lawyerService.findAllDTOAcceptReqLawyer(requestid, fileId);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_LAWYERS));
    }


    @ApiOperation(value = "Delete Lawyer", notes = "RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/deletelawyer", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> deleteLawyer(@RequestParam("mobilenumber") String mobilenumber) {
        lawyerService.deleteLawyer(mobilenumber);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_DELETE_USER, false));
    }
}
