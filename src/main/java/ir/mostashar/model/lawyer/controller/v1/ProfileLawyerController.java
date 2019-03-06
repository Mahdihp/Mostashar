package ir.mostashar.model.lawyer.controller.v1;

import ir.mostashar.model.acceptRequest.service.AcceptRequestService;
import ir.mostashar.model.bill.service.BillService;
import ir.mostashar.model.failRequest.service.FailRequestService;
import ir.mostashar.model.lawyer.dto.LawyerDTO;
import ir.mostashar.model.lawyer.dto.LawyerProfileForm;
import ir.mostashar.model.lawyer.dto.ListLawyerDTO;
import ir.mostashar.model.lawyer.service.LawyerService;
import ir.mostashar.model.notification.service.NotificationService;
import ir.mostashar.model.reminder.service.ReminderService;
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
public class ProfileLawyerController {


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

    @PostMapping(value = "/profile", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> updateProfile(@Valid @RequestBody LawyerProfileForm lpForm) {
        if (lawyerService.updateLawyer(lpForm))
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_SUCESSE));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_LAWYER));
    }

    @PostMapping(value = "/profile/", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    private ResponseEntity<?> findLawyewrProfile(@RequestParam("lawyerid") String lawyerUid) {
        Optional<ListLawyerDTO> list = lawyerService.findListLawyerDTO(2, "", true);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LawyerDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_ONLINE));
    }

}