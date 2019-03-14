package ir.mostashar.model.call.controller.v1;


import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.bill.service.BillService;
import ir.mostashar.model.call.dto.CallDTO;
import ir.mostashar.model.call.dto.CallForm;
import ir.mostashar.model.call.dto.ListCallDTO;
import ir.mostashar.model.call.service.CallService;
import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.service.ClientService;
import ir.mostashar.model.constant.service.ConstantService;
import ir.mostashar.model.factor.dto.FactorForm;
import ir.mostashar.model.factor.service.FactorService;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.service.LawyerService;
import ir.mostashar.model.packsnapshot.PackSnapshot;
import ir.mostashar.model.packsnapshot.service.PackSnapshotService;
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

    @Autowired
    private ConstantService constantService;

    @Autowired
    private PackSnapshotService pssService;

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createCall(@Valid @RequestBody CallForm callForm) {
        if (callService.create(callForm)) {
            Optional<Client> client = clientService.findUserByUid(callForm.getClientId());
            Optional<Lawyer> lawyer = lawyerService.findByUid(callForm.getLawyerId());
            Optional<PackSnapshot> pss = pssService.findPackSnapshotByUid(callForm.getPackSnapshotId());

            FactorForm factorForm = new FactorForm();
            factorForm.setBillId(callForm.getBillId());
            factorForm.setClientName(client.get().getFirstName() + " " + client.get().getLastName());
            factorForm.setServiceDescription(Constants.KEY_CREATE_Price_Not_Percent);
//            factorForm.setTel();
//            factorForm.setAddress();
            int talkTime = DataUtil.getTimeSpent(callForm.getStartTime(), callForm.getEndTime());
            if (callForm.getPurchaseType() == 0) { // در صورت استفاده از پول داخل کیف خود برای خرید بسته

                int basePercent = constantService.findByKey(Constants.KEYS_Percent_Value_Added); //9%
                int pricePerMinute = pss.get().getLawyerPricePerMinute(); //1000

                int priceAllTalk = talkTime * pricePerMinute; // 6000
                int totalPercent = DataUtil.percentPrice(basePercent, priceAllTalk); // 9*600/100 = 540
                int totalPrice = totalPercent + priceAllTalk; // 6540

                // کسر از کیف پول کاربر
                walletService.minusMoneyWallet(client.get().getUid().toString(), client.get().getUid().toString(), totalPrice); //- 6540

                if (lawyer.get().getOrganization() != null) {
                    if (lawyer.get().getOrganization().getMaster() != null) {

                        int percentOrg = lawyer.get().getOrganization().getPercentOrgStock();
                        int percentMaster = lawyer.get().getOrganization().getMaster().getPercentOrgStock();
                        int percentLawyer = lawyer.get().getDefaultPercentStock();

                        int masterMoney = DataUtil.percentPrice(percentMaster, totalPrice);
                        int orgMoney = DataUtil.percentPrice(percentOrg, totalPrice);
                        int lawyerMoney = DataUtil.percentPrice(percentLawyer, totalPrice);

                        walletService.addMoneyWallet(Constants.KEY_MOSTASHAR_WALLET_ID, Constants.KEY_MOSTASHAR_USER_ID, masterMoney); //
                        walletService.addMoneyWallet(lawyer.get().getOrganization().getWallet().getUid().toString(), Constants.KEY_MOSTASHAR_USER_ID, orgMoney); //
                        walletService.addMoneyWallet(lawyer.get().getWallet().getUid().toString(), lawyer.get().getUid().toString(), lawyerMoney); //

                    } else {

                        int percentOrg = lawyer.get().getOrganization().getPercentOrgStock();
                        int percentLawyer = lawyer.get().getDefaultPercentStock();

                        int orgMoney = DataUtil.percentPrice(percentOrg, totalPrice);
                        int lawyerMoney = DataUtil.percentPrice(percentLawyer, totalPrice);

                        walletService.addMoneyWallet(lawyer.get().getOrganization().getWallet().getUid().toString(), Constants.KEY_MOSTASHAR_USER_ID, orgMoney); //
                        walletService.addMoneyWallet(lawyer.get().getWallet().getUid().toString(), lawyer.get().getUid().toString(), lawyerMoney); //
                    }
                } else {
                    int percentMaster = lawyer.get().getOrganization().getMaster().getPercentOrgStock(); //
                    int percentLawyer = lawyer.get().getDefaultPercentStock();

                    int masterMoney = DataUtil.percentPrice(percentMaster, totalPrice);
                    int lawyerMoney = DataUtil.percentPrice(percentLawyer, totalPrice);

                    walletService.addMoneyWallet(Constants.KEY_MOSTASHAR_WALLET_ID, Constants.KEY_MOSTASHAR_USER_ID, masterMoney); //
                    walletService.addMoneyWallet(lawyer.get().getWallet().getUid().toString(), lawyer.get().getUid().toString(), lawyerMoney); //
                }

                factorForm.setValue((long) totalPrice); //6540
                factorService.create(factorForm);

            } else {// در صورت استفاده از امتیاز خود برای خرید بسته
                // talk time 6 min
                int pricePerMinute = pss.get().getLawyerPricePerMinute(); // 1000

                int perScore = constantService.findByKey(Constants.KEYS_Coefficient);
                double totalScore = Math.ceil((talkTime * pricePerMinute * perScore) / 1000); // 6*1000*2 / 1000 = 12

                clientService.minusScore(callForm.getClientId(), totalScore);
                //...

            }

            return ResponseEntity.status(HttpStatus.OK).body(new CallDTO(HttpStatus.OK.value(), Constants.KEY_SUCESSE));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new CallDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_CLIENT_LAWYER));
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
