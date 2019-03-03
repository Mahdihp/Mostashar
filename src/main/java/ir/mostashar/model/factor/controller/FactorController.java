package ir.mostashar.model.factor.controller;


import ir.mostashar.model.bill.service.BillService;
import ir.mostashar.model.factor.dto.FactorDTO;
import ir.mostashar.model.factor.dto.FactorForm;
import ir.mostashar.model.factor.dto.ListFactorDTO;
import ir.mostashar.model.factor.service.FactorService;
import ir.mostashar.model.pack.dto.PackDTO;
import ir.mostashar.model.wallet.service.WalletService;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/client")
public class FactorController {

    @Autowired
    private FactorService factorService;

    @Autowired
    BillService billService;

    @Autowired
    WalletService walletService;

    @PostMapping(value = "/createfactor", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createFactor(@RequestBody FactorForm factorForm) {
        UUID factorUid = factorService.createFactor(factorForm);
        if (factorUid != null)
            return ResponseEntity.status(HttpStatus.OK).body(new FactorDTO(HttpStatus.OK.value(), Constants.KEY_ADD_FACTOR , factorUid.toString()));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new FactorDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_BILL));
    }


    @PostMapping(value = "/factors", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllUserFactor(@RequestParam("billid") String billId, @RequestParam("walletid") String walletId) {
        Optional<ListFactorDTO> factors = factorService.findListFactorDTO(-1L, billId, 4);
        if (factors.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(factors.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new FactorDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_FACTOR));
    }


}
