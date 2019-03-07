package ir.mostashar.model.lawyer.controller.v1;

import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.BaseDTO;
import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.dto.RegisterClientDTO;
import ir.mostashar.model.client.dto.ValidateCode;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.service.LawyerService;
import ir.mostashar.model.role.Role;
import ir.mostashar.model.role.RoleName;
import ir.mostashar.security.jwt.JwtResponse;
import ir.mostashar.security.jwt.JwtUtil;
import ir.mostashar.utils.Constants;
import ir.mostashar.utils.DataUtil;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/lawyers/auth")
public class AuthLawyerController {

    @Autowired
    LawyerService lawyerService;

    @Autowired
    JwtUtil jwtUtil;

    @ApiOperation(value = "Login Lawyer with phoneNumber & advicetype", notes = " 1 = روانشناسی ,2 = حقوق : advicetype" +"\n"+ " RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> signUp(@RequestParam("mobilenumber") String mobileNumber, @RequestParam("advicetype") int advicetype) {
        if (!DataUtil.isValideMobileNumber(mobileNumber))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseDTO(HttpStatus.BAD_REQUEST.value(), Constants.KEY_PHONE_NUMBER_NOT_VALID, false));

        Optional<Lawyer> lawyer = lawyerService.findByMobileNumber(mobileNumber);
        if (lawyer.isPresent()) {
            lawyerService.reSendCode(mobileNumber);
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_LOGIN, lawyer.get().getUid().toString(), false));
        }

        Role role = new Role();
        role.setUid(UUID.randomUUID());
        role.setName(RoleName.ROLE_LAWYER);
        role.setUserDefined(true);
        role.setDescription(RoleName.ROLE_LAWYER.name().toLowerCase());

        Optional<String> uuid = lawyerService.registerUser(mobileNumber, advicetype);
        if (uuid.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_REGISTER, uuid.get(), false));
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseDTO(500, Constants.KEY_CREATE_FILE_FAILED, false));

    }

    @PostMapping(value = "/validatecode", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> validateCode(@RequestParam("codeOff") String code, @RequestParam("clientId") String userId) {
        if (TextUtils.isEmpty(code) && TextUtils.isEmpty(userId))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseDTO(HttpStatus.BAD_REQUEST.value(), Constants.KEY_INVALID_CODE, false));

        Optional<Lawyer> lawyer = lawyerService.findByUserUidAndCode(userId, code);
        ValidateCode validateCode = new ValidateCode(code, userId);
        if (lawyer.isPresent()) {
            JwtResponse jwtResponse = jwtUtil.generateToken(validateCode);

            if (jwtResponse != null) {
                UUID walletUid = null;
                if (lawyerService.activateUser(true, lawyer.get().getUid())) {
                    walletUid = lawyerService.createWallletUser(lawyer.get());
                }
                System.out.println("Log------------------JwtResponse " + jwtResponse.toString());
                RegisterClientDTO registerClientDTO = new RegisterClientDTO(HttpStatus.OK.value(), Constants.KEY_CODE_VERIFY, lawyer.get().getUid().toString(), walletUid.toString(), true, jwtResponse);

                return ResponseEntity.status(HttpStatus.OK).body(registerClientDTO);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new BaseDTO(HttpStatus.UNAUTHORIZED.value(), Constants.KEY_INVALID_CODE, "", false));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_INVALID_CODE, false));

    }


}
