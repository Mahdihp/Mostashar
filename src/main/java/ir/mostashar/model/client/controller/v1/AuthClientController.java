package ir.mostashar.model.client.controller.v1;

import ir.mostashar.model.BaseDTO;
import ir.mostashar.model.user.dto.SignUpForm;
import ir.mostashar.model.client.service.ClientDetailsServiceImpl;
import ir.mostashar.util.Constants;
import ir.mostashar.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/api/auth/client")
public class AuthClientController {

    @Autowired
    ClientDetailsServiceImpl clientDetailsService;

    @PostMapping(value = "/signup", consumes = {"application/json;charset=UTF-8"},produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> registerPhoneNumber(@RequestBody SignUpForm signUpForm) {
//        System.out.println("Log--------------registerPhoneNumber "+Long.valueOf(signUpForm.getPhoneNumber()));
//        System.out.println("Log--------------registerPhoneNumber "+ clientDetailsService.existsByPhoneNumber(Long.valueOf(signUpForm.getPhoneNumber())));
        String phone = "0"+Long.valueOf(signUpForm.getPhoneNumber());
        if (!DataUtil.isValidePhoneNumber(phone)){
            return new ResponseEntity<>(new BaseDTO(HttpStatus.BAD_REQUEST.value()+"",Constants.KEY_PHONE_NUMBER_NOT_VALID),HttpStatus.BAD_REQUEST);
        }
        if (clientDetailsService.existsByPhoneNumber(Long.valueOf(signUpForm.getPhoneNumber()))) {
            return new ResponseEntity<>(new BaseDTO(HttpStatus.BAD_REQUEST.value()+"",Constants.KEY_REGISTER_ALREADY),HttpStatus.BAD_REQUEST);
        }
        // بررسی صحبت فرمت موبایل
        clientDetailsService.registerPhoneNumberAndRole(signUpForm);
        return ResponseEntity.ok().body(Constants.KEY_REGISTER);
    }
}
