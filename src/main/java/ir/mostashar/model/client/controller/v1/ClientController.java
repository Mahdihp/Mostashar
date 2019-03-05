package ir.mostashar.model.client.controller.v1;

import io.swagger.annotations.Api;
import ir.mostashar.model.acceptRequest.dto.ListAcceptRequestDTO;
import ir.mostashar.model.acceptRequest.service.AcceptRequestService;
import ir.mostashar.model.bill.dto.BillDTO;
import ir.mostashar.model.bill.dto.ListBillDTO;
import ir.mostashar.model.bill.service.BillService;
import ir.mostashar.model.client.dto.ClientDTO;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.repository.LawyerRepo;
import ir.mostashar.model.notification.Notification;
import ir.mostashar.model.notification.service.NotificationService;
import ir.mostashar.model.pack.BuyPackStatus;
import ir.mostashar.model.pack.dto.BuyPackDTO;
import ir.mostashar.model.pack.dto.BuyPackForm;
import ir.mostashar.model.pack.dto.ListPackDTO;
import ir.mostashar.model.pack.dto.PackDTO;
import ir.mostashar.model.pack.service.PackService;
import ir.mostashar.model.reminder.dto.ListReminderDTO;
import ir.mostashar.model.reminder.service.ReminderService;
import ir.mostashar.model.request.RequestStatus;
import ir.mostashar.model.request.service.RequestService;
import ir.mostashar.model.wallet.Wallet;
import ir.mostashar.utils.Constants;
import ir.mostashar.utils.DataUtil;
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
@RequestMapping("/api/v1/clients")
public class ClientController {

    @Autowired
    LawyerRepo lawyerRepo;

    @Autowired
    PackService packService;

    @Autowired
    AcceptRequestService arService;

    @Autowired
    RequestService requestService;

    @Autowired
    ReminderService reminderService;

    @Autowired
    NotificationService nService;

    @Autowired
    BillService billService;

    /**
     * find All Lawyer Read Request File
     * @param requestId
     * @return
     */
    @PostMapping(value = "/readlawyers", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findReadLawyers(@RequestParam("requestid") String requestId) {
        Optional<Notification> notification = nService.findByRequestUid(requestId);
        if (notification.isPresent()){
            Optional<ListReminderDTO> list = reminderService.findListReminderDTOByNotifyUid(notification.get().getUid().toString());
            if (list.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(list.get());
            }else{
                return ResponseEntity.status(HttpStatus.OK).body(new ClientDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_LAWYER_READ));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ClientDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_REQUEST));
    }

    /**
     * Find All Lawyer Accept Request File
     * @param fileId
     * @param requestId
     * @return
     */
    @PostMapping(value = "/acceptlawyer", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAcceptLawyer(@RequestParam("fileid") String fileId, @RequestParam("requestid") String requestId) {
        Optional<ListAcceptRequestDTO> list = arService.findListAcceptRequestDTO(requestId, fileId);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ClientDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_LAWYER_ACCEPT));
    }

    /**
     * Assign Lawyer to Request File
     * Update RequestStatus Feild to WAIT_PEYMENT
     * @param lawyerId
     * @param requestId
     * @return
     */
    @PostMapping(value = "/assinglawyer", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> assingLawyerByClient(@RequestParam("lawyerid") String lawyerId, @RequestParam("requestid") String requestId) {
        if (arService.assignLawyerToRequest(lawyerId, requestId, true)) {
            requestService.updateStatusRequest(requestId, RequestStatus.WAIT_PEYMENT);
            return ResponseEntity.status(HttpStatus.OK).body(new ClientDTO(HttpStatus.OK.value(), Constants.KEY_ASSIGN_LAWYER_TO_REQUEST));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ClientDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_REQUEST));
    }

    /**
     * Reject Lawyer from Client.
     * Update RequestStatus Feild to SELECT_LAWYER
     * @param lawyerId
     * @param requestId
     * @return
     */
    @PostMapping(value = "/rejectlawyer", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> rejectedLawyerByClient(@RequestParam("lawyerid") String lawyerId, @RequestParam("requestid") String requestId) {
        if (arService.assignLawyerToRequest(lawyerId, requestId, false)) {
            requestService.updateStatusRequest(requestId, RequestStatus.SELECT_LAWYER);
            return ResponseEntity.status(HttpStatus.OK).body(new ClientDTO(HttpStatus.OK.value(), Constants.KEY_REJECT_LAWYER_TO_REQUEST));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ClientDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_REQUEST));
    }

    /**
     * Find All Packs.
     * @param lawyerid
     * @return
     */
    @PostMapping(value = "/packs", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllPackByLawyer(@RequestParam("lawyerid") String lawyerid) {
        if (!DataUtil.isValidUUID(lawyerid))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PackDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_UUID_NOT_VALID));

        Optional<Lawyer> lawyer = lawyerRepo.findByUid(UUID.fromString(lawyerid));
        if (lawyer.isPresent()) {
            Optional<ListPackDTO> allPacks = packService.findAllPacks(lawyer.get().getPricePerMinute());
            if (allPacks.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(allPacks.get());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PackDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_LAWYER));
    }

    /**
     * Buy Pack
     * Add ConsumptionPack Table
     * Add PackSnapshot Table
     * @param buyPackForm
     * @return
     */
    @PostMapping(value = "/buypack", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> buyPack(@Valid @RequestBody BuyPackForm buyPackForm) {
        Optional<BuyPackStatus> buyPack = packService.createBuyPack(buyPackForm);
        if (buyPack.isPresent()) {
            switch (buyPack.get().getBuyPack()) {
                case ComplateAll:
                    return ResponseEntity.status(HttpStatus.OK).body(new BuyPackDTO(HttpStatus.OK.value(), Constants.KEY_SUCESSE));
                case ErrorAll:
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PackDTO(HttpStatus.BAD_REQUEST.value(), Constants.KEY_FAIL));
                case ConsumptionPackError:
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PackDTO(HttpStatus.BAD_REQUEST.value(), Constants.KEY_FAIL_CONSUMPTIONPACK));
                case PackSnapshotError:
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PackDTO(HttpStatus.BAD_REQUEST.value(), Constants.KEY_FAIL));
//                case FactorError:
//                    return ResponseEntity.status(HttpStatus.OK).body(new PackDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_FAIL_FACTOR));
            }
        }
        return null;
    }

    @PostMapping(value = "/bills", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllBillsClient(@RequestParam("clientid") String clientUid,@RequestParam("walletid") String walletUid) {
        Optional<ListBillDTO> list = billService.findListBillDTOByWalletUid(walletUid, clientUid);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new ClientDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_BILL));
    }

    // درخواست اعمال کد تخفیف
    // Update Score per peyment price User.


}
