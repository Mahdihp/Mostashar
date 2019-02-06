package ir.mostashar.model.client.controller.v1;

import ir.mostashar.model.BaseDTO;
import ir.mostashar.model.client.dto.SignUpForm;
import ir.mostashar.model.client.dto.ValidateCode;
import ir.mostashar.model.client.service.UserServiceImpl;
import ir.mostashar.model.user.User;
import ir.mostashar.util.Constants;
import ir.mostashar.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping(value = "/login", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> signUp(@RequestBody SignUpForm signUpForm) {
        if (!DataUtil.isValidePhoneNumber(signUpForm.getPhoneNumber())) {
            return new ResponseEntity<>(new BaseDTO(HttpStatus.BAD_REQUEST.value() + "", Constants.KEY_PHONE_NUMBER_NOT_VALID, "", false), HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByPhoneNumber(Long.valueOf(signUpForm.getPhoneNumber()))) {
            return new ResponseEntity<>(new BaseDTO(HttpStatus.BAD_REQUEST.value() + "", Constants.KEY_REGISTER_ALREADY, "", false), HttpStatus.BAD_REQUEST);
        }
        String uuid = userService.registerPhoneNumberAndRole(signUpForm);
        return ResponseEntity.ok().body(new BaseDTO(HttpStatus.OK.value() + "", Constants.KEY_REGISTER, uuid, false));
    }

    @PostMapping(value = "/validateCode", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> validateCode(@Valid @RequestBody ValidateCode validateCode) {
        User user = userService.findUUIDAndCode(validateCode.getUserid(), validateCode.getCode());
        if (user != null) {
            userService.setActiveUser(true, user.getUid());
            return ResponseEntity.ok().body(new BaseDTO(HttpStatus.OK.value() + "", Constants.KEY_CODE_VERIFY, user.getUid().toString(), true));
        } else
            return ResponseEntity.ok().body(new BaseDTO(HttpStatus.NOT_FOUND.value() + "", Constants.KEY_INVALID_CODE, "", false));
    }
}
