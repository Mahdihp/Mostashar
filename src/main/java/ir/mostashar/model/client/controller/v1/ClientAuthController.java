package ir.mostashar.model.client.controller.v1;

import ir.mostashar.model.BaseDTO;
import ir.mostashar.model.client.dto.ClientDTO;
import ir.mostashar.model.client.dto.FileForm;
import ir.mostashar.model.client.dto.SignUpForm;
import ir.mostashar.model.client.dto.ValidateCode;
import ir.mostashar.model.client.service.UserServiceImpl;
import ir.mostashar.model.file.dto.FileDTO;
import ir.mostashar.model.user.User;
import ir.mostashar.security.jwt.JwtResponse;
import ir.mostashar.util.Constants;
import ir.mostashar.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/client/auth")
public class ClientAuthController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping(value = "/login", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> signUp(@RequestBody SignUpForm signUpForm) {
        if (!DataUtil.isValidePhoneNumber(signUpForm.getPhoneNumber())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseDTO(HttpStatus.BAD_REQUEST.value() + "", Constants.KEY_PHONE_NUMBER_NOT_VALID, "", false));
        }
        if (userService.existsByPhoneNumber(Long.valueOf(signUpForm.getPhoneNumber()))) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseDTO(HttpStatus.BAD_REQUEST.value() + "", Constants.KEY_REGISTER_ALREADY, "", false));
        }
        Optional<String> uuid = userService.registerPhoneNumberAndRole(signUpForm);
        if (uuid.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value() + "", Constants.KEY_REGISTER, uuid.get(), false));
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseDTO("500", Constants.KEY_CREATE_FILE_FAILED, "", false));

    }

    @PostMapping(value = "/validateCode", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> validateCode(@Valid @RequestBody ValidateCode validateCode) {
        Optional<User> user = userService.findUserIdAndCode(validateCode.getUserid(), validateCode.getCode());
        if (user.isPresent()) {
            JwtResponse jwtResponse = userService.generateToken(validateCode);
            if (jwtResponse != null) {
                userService.activeUser(true, user.get().getUid());
                System.out.println("Log------------------JwtResponse " + jwtResponse.toString());
                ClientDTO clientDTO = new ClientDTO(HttpStatus.OK.value() + "", Constants.KEY_CODE_VERIFY, user.get().getUid().toString(), true, jwtResponse);
                return ResponseEntity.status(HttpStatus.OK).body(clientDTO);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new BaseDTO(HttpStatus.UNAUTHORIZED.value() + "", Constants.KEY_INVALID_CODE, "", false));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseDTO(HttpStatus.NOT_FOUND.value() + "", Constants.KEY_INVALID_CODE, "", false));

    }

}
