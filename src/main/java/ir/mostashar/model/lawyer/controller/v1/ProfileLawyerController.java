package ir.mostashar.model.lawyer.controller.v1;

import ir.mostashar.model.acceptRequest.service.AcceptRequestService;
import ir.mostashar.model.bill.service.BillService;
import ir.mostashar.model.failRequest.service.FailRequestService;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.dto.HomePageDTO;
import ir.mostashar.model.lawyer.dto.LawyerDTO;
import ir.mostashar.model.lawyer.dto.LawyerProfileForm;
import ir.mostashar.model.lawyer.service.LawyerService;
import ir.mostashar.model.notification.service.NotificationService;
import ir.mostashar.model.reminder.service.ReminderService;
import ir.mostashar.model.wallet.Wallet;
import ir.mostashar.model.wallet.service.WalletService;
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
    private WalletService walletService;


    @PostMapping(value = "/profile", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> updateProfile(@Valid @RequestBody LawyerProfileForm lpForm) {
        if (lawyerService.updateLawyer(lpForm))
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_SUCESSE));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_LAWYER));
    }


    @PostMapping(value = "/viewprofile", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> viewProfile(@RequestParam("userid") String userid) {
        Optional<Lawyer> lawyer = lawyerService.findLawyerUidAndActive(userid, true);
        if (lawyer.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(lawyer.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_LAWYER));
    }

    @PostMapping(value = "/profile/", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    private ResponseEntity<?> findLawyewrProfile(@RequestParam("lawyerid") String lawyerId) {
        Optional<LawyerDTO> list = lawyerService.findLawyerDTOByUid(1, lawyerId);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_LAWYER));
    }

    @PostMapping(value = "/homepage/", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    private ResponseEntity<?> homePageInfo(@RequestParam("lawyerid") String lawyerId) {
        Optional<Lawyer> lawyer = lawyerService.findByUid(lawyerId);
        Optional<Wallet> wallet = walletService.findByUserId(lawyerId);
        int money = 0;
        boolean avalabe = false;
        if (wallet.isPresent()) {
            money = wallet.get().getValue();
        }
        if (lawyer.isPresent()) {
            avalabe = lawyer.get().getAvailable();
            HomePageDTO homePageDTO = new HomePageDTO();
            homePageDTO.setStatus(HttpStatus.OK.value());
            homePageDTO.setMessage(Constants.KEY_SUCESSE);
            homePageDTO.setMoney(String.valueOf(money));
            homePageDTO.setAvalable(avalabe);
            return ResponseEntity.status(HttpStatus.OK).body(homePageDTO);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_LAWYER));
        }
    }

    @PostMapping(value = "/profile_bank", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> updateProfileBank(@RequestParam("lawyerid") String lawyerUid,
                                               @RequestParam("shababank") String shabaBank,
                                               @RequestParam("shabacode") String shabaCode) {
        Lawyer lawyer = lawyerService.findByUid(lawyerUid).get();
//        lawyer.setShabaBank(shabaBank);
//        lawyer.setShabaCode(shabaCode);

        if (lawyerService.updateLawyer(lawyer))
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_SUCESSE));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_LAWYER));
    }

}