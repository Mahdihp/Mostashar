package ir.mostashar.model.bill.controller;


import ir.mostashar.model.bill.dto.BillDTO;
import ir.mostashar.model.bill.dto.BillForm;
import ir.mostashar.model.bill.dto.ListBillDTO;
import ir.mostashar.model.bill.service.BillService;
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
@RequestMapping("/api/v1/client")
public class BillController {

    @Autowired
    BillService billService;

    @Autowired
    WalletService walletService;

    @PostMapping(value = "/createbill", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createBill(@Valid @RequestBody BillForm billForm) {
        if (billService.createBill(billForm)) {
            if (walletService.addMoneyWallet(billForm.getWalletId(), billForm.getUserId(), billForm.getValue())) {
                return ResponseEntity.status(HttpStatus.OK).body(new BillDTO(HttpStatus.OK.value(), Constants.KEY_ADD_BILL_ADD_WALLET));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BillDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_WALLET));
    }

    @PostMapping(value = "/bills", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findBillByUserAndWallet(@RequestParam("userid") String userId, @RequestParam("walletid") String walletId) {
        Optional<Wallet> wallet = walletService.findByWalletUidAndUserUid(walletId, userId);
        if (wallet.isPresent()) {
            Optional<ListBillDTO> bills = billService.findListBillDTOByWalletUid(wallet.get().getUid().toString());
            if (bills.isPresent())
                return ResponseEntity.status(HttpStatus.OK).body(bills.get());
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BillDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_BILL));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BillDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_WALLET));
    }


}
