package ir.mostashar.model.bill.controller.v1;


import ir.mostashar.model.bill.dto.BillDTO;
import ir.mostashar.model.bill.dto.BillForm;
import ir.mostashar.model.bill.service.BillService;
import ir.mostashar.model.request.RequestStatus;
import ir.mostashar.model.request.service.RequestService;
import ir.mostashar.model.wallet.service.WalletService;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/bills")
public class BillController {

    @Autowired
    BillService billService;

    @Autowired
    WalletService walletService;

    @Autowired
    RequestService requestService;

    @Transactional
    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createBill(@Valid @RequestBody BillForm billForm) {
        if (billService.create(billForm)) {
            if (walletService.addMoneyWallet(billForm.getWalletId(), billForm.getUserId(), billForm.getValue())) {
                requestService.updateStatusRequest(billForm.getRequestId(), RequestStatus.WAIT_CALL);
                return ResponseEntity.status(HttpStatus.OK).body(new BillDTO(HttpStatus.OK.value(), Constants.KEY_ADD_BILL_ADD_WALLET));
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(new BillDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_WALLET));
    }




}
