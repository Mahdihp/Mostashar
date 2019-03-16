package ir.mostashar.model.lawyer.controller.v1;

import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.BaseDTO;
import ir.mostashar.model.client.dto.RegisterClientDTO;
import ir.mostashar.model.client.dto.ValidateCode;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.dto.LawyerDTO;
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

    @ApiOperation(value = "SignIn Lawyer with phoneNumber & advicetype", notes = " 1 = روانشناسی ,2 = حقوق : advicetype" + "\n" + " RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/signin", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> signIn(@RequestParam("mobilenumber") String mobileNumber, @RequestParam("advicetype") int advicetype) {
        if (!DataUtil.isValideMobileNumber(mobileNumber))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_PHONE_NUMBER_NOT_VALID, false));

        Optional<Lawyer> lawyer = lawyerService.findByMobileNumber(mobileNumber);
        if (lawyer.isPresent()) {
            lawyerService.reSendCode(mobileNumber);
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_LOGIN, lawyer.get().getUid().toString(), false));
        }

        Role role = new Role();
        role.setUid(UUID.randomUUID());
        role.setName(RoleName.ROLE_LAWYER);
        role.setUserDefined(true);
        role.setDescription(RoleName.ROLE_LAWYER.name().toLowerCase());

        Optional<UUID> lawyerId = lawyerService.registerUser(mobileNumber, advicetype);
        if (lawyerId.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_REGISTER, lawyerId.get().toString(), false));
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseDTO(500, Constants.KEY_CREATE_FILE_FAILED, false));

    }

    @ApiOperation(value = "Validate Code", notes = "RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/validationcode", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> validationCode(@RequestParam("lawyerid") String lawyerId, @RequestParam("code") String code) {
        if (TextUtils.isEmpty(code) && TextUtils.isEmpty(lawyerId))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_INVALID_CODE, false));

        Optional<Lawyer> lawyer = lawyerService.findByUserUidAndCode(lawyerId, code);
        ValidateCode validateCode = new ValidateCode(code, lawyerId);
        if (lawyer.isPresent()) {
            JwtResponse jwtResponse = jwtUtil.generateToken(validateCode);

            if (jwtResponse != null) {
                UUID walletUid = null;
                if (lawyerService.activateUser(true, lawyer.get().getUid())) {
                    walletUid = lawyerService.createWallletUser(lawyer.get());
                }
                RegisterClientDTO registerClientDTO = new RegisterClientDTO(HttpStatus.OK.value(), Constants.KEY_CODE_VERIFY, lawyer.get().getUid().toString(), walletUid.toString(), true, jwtResponse);
                System.out.println("Log------------------JwtResponse " + registerClientDTO.toString());

                return ResponseEntity.status(HttpStatus.OK).body(registerClientDTO);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_INVALID_CODE, false));
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_INVALID_CODE, false));

    }


}
