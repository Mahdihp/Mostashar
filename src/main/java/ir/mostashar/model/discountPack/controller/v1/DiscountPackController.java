package ir.mostashar.model.discountPack.controller.v1;


import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.discountPack.dto.DiscountPackForm;
import ir.mostashar.model.discountPack.service.DiscountPackService;
import ir.mostashar.model.file.dto.FileDTO;
import ir.mostashar.model.wallet.dto.WalletDTO;
import ir.mostashar.model.wallet.dto.WalletForm;
import ir.mostashar.model.wallet.service.WalletService;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/discountpacks")
public class DiscountPackController {


//    @Autowired
//    DiscountPackService dpService;


   /* @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createDiscountPack(@Valid @RequestBody DiscountPackForm dpForm) {
        dpService.createDiscountPack(dpForm);
        return ResponseEntity.status(HttpStatus.OK).body(new WalletDTO(HttpStatus.OK.value(), Constants.KEY_UPDATE_WALLET));
//        else
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FileDTO(HttpStatus.BAD_REQUEST.value(), Constants.KEY_USER_NOT_FOUND));
    }*/
}
