package ir.mostashar.model.call.controller.v1;


import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.bill.Bill;
import ir.mostashar.model.bill.service.BillService;
import ir.mostashar.model.call.dto.CallDTO;
import ir.mostashar.model.call.dto.CallForm;
import ir.mostashar.model.call.dto.ListCallDTO;
import ir.mostashar.model.call.service.CallService;
import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.service.ClientService;
import ir.mostashar.model.factor.dto.FactorForm;
import ir.mostashar.model.factor.service.FactorService;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.service.LawyerService;
import ir.mostashar.model.wallet.Wallet;
import ir.mostashar.model.wallet.service.WalletService;
import ir.mostashar.utils.Constants;
import ir.mostashar.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/calls")
public class CallController {

    @Autowired
    private CallService callService;

    @Autowired
    private FactorService factorService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private BillService billService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private LawyerService lawyerService;

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createCall(@Valid @RequestBody CallForm callForm) {
        if (callService.create(callForm)) {
            Optional<Bill> bill = billService.findBillByUid(callForm.getBillId());
            Optional<Client> client = clientService.findUserByUid(callForm.getClientId());
            Optional<Wallet> wallet = walletService.findByUid(bill.get().getWallet().getUid().toString(), false);
            Optional<Lawyer> lawyer = lawyerService.findByUid(callForm.getLawyerId());
            FactorForm factorForm = new FactorForm();
            factorForm.setBillId(callForm.getBillId());
            factorForm.setClientName(client.get().getFirstName() + " " + client.get().getLastName());
            factorForm.setServiceDescription(Constants.KEY_CREATE_CALL_DES);
//            factorForm.setTel();
//            factorForm.setAddress();
            // 10:00 to 10:05
            // 5 - wallet
            int leftMinute = DataUtil.getTimeSpent(callForm.getStartTime(), callForm.getEndTime());
            if (callForm.getPurchaseType() == 0) {
                // در صورت استفاده از پول داخل کیف خود برای خرید بسته
//                 leftMinuteTotal = leftMinute
//                leftMinute - wallet.get().getValue()
//                walletService.minusMoneyWallet(wallet.get().getUid().toString(),client.get().getUid().toString(),leftTotalMoney);
//                factorForm.setValue(leftTotalMoney);

            } else {
                // در صورت استفاده از امتیاز خود برای خرید بسته
                int clientScore = client.get().getScore();
                int lawyerScore = lawyer.get().getScore();
                clientService.minusScore(callForm.getClientId(), leftMinute);
                lawyerService.addScore(callForm.getLawyerId(), clientScore);

            }
            factorService.create(factorForm);
            return ResponseEntity.status(HttpStatus.OK).body(new CallDTO(HttpStatus.OK.value(), Constants.KEY_SUCESSE));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CallDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_CLIENT_LAWYER));
    }

    /**
     * find Calls List By client or lawyer or request
     *
     * @param userid
     * @return
     */
    @ApiOperation(value = "Find All List Calls", notes = "type=1,2,3 \n 1=client & 2=lawyer & 3=request")
    @PostMapping(value = "/all", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllCallFromClient(@RequestParam("type") int type, @RequestParam("userid") String userid) {
        Optional<ListCallDTO> calls = callService.findListCallDTOByUid(type, userid);
        if (calls.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(calls.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CallDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_CALL));
    }


}
