package ir.mostashar.model.wallet.controller;

import ir.mostashar.model.client.dto.FileForm;
import ir.mostashar.model.file.dto.FileDTO;
import ir.mostashar.model.file.dto.ListFileDTO;
import ir.mostashar.model.wallet.dto.WalletDTO;
import ir.mostashar.model.wallet.dto.WalletForm;
import ir.mostashar.model.wallet.service.WalletService;
import ir.mostashar.utils.Constants;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {


    @Autowired
    private WalletService walletService;

    @PostMapping(value = "/updatewallet", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> updateWallet(@Valid @RequestBody WalletForm walletForm) {
        if (walletService.updateWallet(walletForm, false))
            return ResponseEntity.status(HttpStatus.OK).body(new WalletDTO(HttpStatus.OK.value(), Constants.KEY_UPDATE_WALLET));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FileDTO(HttpStatus.BAD_REQUEST.value(), Constants.KEY_USER_NOT_FOUND));
    }




}
