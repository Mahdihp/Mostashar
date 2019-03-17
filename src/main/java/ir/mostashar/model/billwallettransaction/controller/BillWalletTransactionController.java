package ir.mostashar.model.billwallettransaction.controller;

import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.BaseDTO;
import ir.mostashar.model.billwallettransaction.dto.BillWalletTransactionDTO;
import ir.mostashar.model.billwallettransaction.dto.ListBillWalletTransactionDTO;
import ir.mostashar.model.billwallettransaction.service.BillWalletTransactionService;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/17/19
 * Time: 10:51
 * https://github.com/mahdihp
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/bwt")
public class BillWalletTransactionController {

    @Autowired
    private BillWalletTransactionService bwtService;

    @ApiOperation(value = "Find All Bill Wallet Transaction", notes = "type=1,2,3 1=Find By Request Id & 2=Find By User Id")
    @PostMapping(value = "/all", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllBwt(@RequestParam("type") int type, @RequestParam("item") String item) {
        Optional<ListBillWalletTransactionDTO> list = bwtService.findAllDTO(type, item);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_BWT));
    }

    @PostMapping(value = "/one", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findBwt(@RequestParam("bwtid") String bwtId) {
        Optional<BillWalletTransactionDTO> bwt = bwtService.findDTOById(bwtId);
        if (bwt.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(bwt.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_BWT));
    }


}
