package ir.mostashar.model.userpopularity.controller;

import ir.mostashar.model.BaseDTO;
import ir.mostashar.model.userpopularity.dto.ListUserPopularityDTO;
import ir.mostashar.model.userpopularity.service.UserPopularityService;
import ir.mostashar.utils.Constants;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/client")
public class UserPopularityController {

    @Autowired
    UserPopularityService upService;

    @PostMapping(value = "/createuserpopularity", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createUserPopularity(@RequestParam("userid") String userid, @RequestParam("userpopu") String userpopu) {
        if (TextUtils.isEmpty(userid) || TextUtils.isEmpty(userpopu))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_USER_NOT_FOUND, false));

        if (upService.existsPopu(userid,userpopu))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_USER_POPU_ALDEADY, false));

        if (upService.createUserPopularity(userid, userpopu))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_ADD_USER_POPULARITY, false));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_USER, false));
    }

    @PostMapping(value = "/alluserpopularity", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllUserPopularity(@RequestParam("userid") String userid) {
        System.out.println("Log---findAllUserPopularity--------------------1");

        if (TextUtils.isEmpty(userid))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_USER, false));
        Optional<ListUserPopularityDTO> allUserPopu = upService.findAllDTOByUser(userid);

        if (allUserPopu.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(allUserPopu.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_USER, false));
    }

    @PostMapping(value = "/removeuserpopularity", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> deleteUserPopularity(@RequestParam("userid") String userid, @RequestParam("userpopu") String userpopu) {
        if (TextUtils.isEmpty(userid) && TextUtils.isEmpty(userpopu))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_USER_NOT_FOUND, false));

        if (upService.deleteUserPopularity(userid, userpopu))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_DELETE_USER_POPULARITY, false));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_FAIL, false));

    }


}
