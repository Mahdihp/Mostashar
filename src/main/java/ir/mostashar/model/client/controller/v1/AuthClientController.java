package ir.mostashar.model.client.controller.v1;

import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.BaseDTO;
import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.dto.ClientDTO;
import ir.mostashar.model.client.dto.RegisterClientDTO;
import ir.mostashar.model.client.dto.ValidateCode;
import ir.mostashar.model.client.service.ClientService;
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
@RequestMapping("/api/v1/clients/auth")
public class AuthClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    JwtUtil jwtUtil;

    @ApiOperation(value = "SignIn Client with mobileNumber", notes = "RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/signin", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> signIn(@RequestParam("mobilenumber") String mobileNumber) {
        if (!DataUtil.isValideMobileNumber(mobileNumber))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_PHONE_NUMBER_NOT_VALID));

        Optional<Client> client = clientService.findByMobileNumber(mobileNumber);
        if (client.isPresent()) {
            clientService.reSendCode(mobileNumber);
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_LOGIN, client.get().getUid().toString(), false));
        }

        Role role = new Role();
        role.setUid(UUID.randomUUID());
        role.setName(RoleName.ROLE_CLIENT);
        role.setUserDefined(true);
        role.setDescription(RoleName.ROLE_CLIENT.name().toLowerCase());

        Optional<UUID> clientId = clientService.registerUser(mobileNumber);
        if (clientId.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(new ClientDTO(HttpStatus.OK.value(), Constants.KEY_REGISTER, clientId.get().toString(), false));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new ClientDTO(HttpStatus.OK.value(), Constants.KEY_CREATE_FILE_FAILED, false));

    }

    @ApiOperation(value = "Validate Code", notes = "RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/validationcode", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> validationCode(@RequestParam("clientid") String clientId, @RequestParam("code") String code) {
        if (TextUtils.isEmpty(code) && TextUtils.isEmpty(clientId))
            return ResponseEntity.status(HttpStatus.OK).body(new ClientDTO(HttpStatus.OK.value(), Constants.KEY_INVALID_CODE, false));

        Optional<Client> client = clientService.findByUserUidAndCode(clientId, code);
        ValidateCode validateCode = new ValidateCode(code, clientId);
        if (client.isPresent()) {
            JwtResponse jwtResponse = jwtUtil.generateToken(validateCode);

            if (jwtResponse != null) {
                UUID walletUid = null;
                if (clientService.activateUser(true, client.get().getUid())) {
                    walletUid = clientService.createWallletUser(client.get());
                }
                System.out.println("Log------------------JwtResponse " + jwtResponse.toString());
                RegisterClientDTO registerClientDTO = new RegisterClientDTO(HttpStatus.OK.value(), Constants.KEY_CODE_VERIFY, client.get().getUid().toString(), walletUid.toString(), true, jwtResponse);

                return ResponseEntity.status(HttpStatus.OK).body(registerClientDTO);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_INVALID_CODE, "", false));
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_INVALID_CODE, false));

    }


}

