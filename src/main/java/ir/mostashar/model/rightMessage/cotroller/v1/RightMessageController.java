package ir.mostashar.model.rightMessage.cotroller.v1;

import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.bill.dto.ListBillDTO;
import ir.mostashar.model.lawyer.dto.LawyerDTO;
import ir.mostashar.model.lawyer.service.LawyerService;
import ir.mostashar.model.rightMessage.RightMessage;
import ir.mostashar.model.rightMessage.service.RightMessageService;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by mnm
 * User: mnm
 * Date: 3/9/19
 * Time: 14:20
 * http://github.com/ghaseminya
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/messages")
public class RightMessageController {
    @Autowired
    RightMessageService rightMessageService;

    @ApiOperation(value = "list of message between lawyer and client for home page",
            notes ="RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/byclient", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> getmessages(@RequestParam("lawyerid") String lawyerId) {
        Optional<RightMessage> list = rightMessageService.findRightMessageBylawyer(lawyerId);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_BILL));
    }



}
