package ir.mostashar.model.userFeedback.controller;


import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.BaseDTO;
import ir.mostashar.model.userFeedback.dto.UserFeedBackForm;
import ir.mostashar.model.userFeedback.service.UserFeedBackService;
import ir.mostashar.model.wallet.dto.WalletForm;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/userfeedbacks")
public class UserFeedBackController {

    @Autowired
    UserFeedBackService ufbService;

    @ApiOperation(value = "Create User FeedBack", notes = "RequestBody :" + MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createFeedBack(@Valid @RequestBody UserFeedBackForm udbForm) {
        if (ufbService.createUserFeedBack(udbForm))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_SUCESSE));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_FAIL));
    }
}
