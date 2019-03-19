package ir.mostashar.model.acceptRequest.controller.v1;

import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.BaseDTO;
import ir.mostashar.model.acceptRequest.dto.ListAcceptRequestDTO;
import ir.mostashar.model.acceptRequest.service.AcceptRequestService;
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
 * Time: 17:32
 * http://github.com/ghaseminya
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/acceptrequest")
public class AcceptRequestController {

    @Autowired
    private AcceptRequestService acceptRequestService;

    @ApiOperation(value = "list of accepted request by lawyer",
            notes ="RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/projects", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAcceptedRequestLawyer(@RequestParam("lawyerid") String lawyerId) {
        Optional<ListAcceptRequestDTO> list = acceptRequestService.findAllDTOByLawyer(lawyerId);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new ListAcceptRequestDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_REQUEST));
    }

    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> addAcceptRequest(@RequestParam("acceptrequestid") String acceptRequestId,
                                              @RequestParam("lawyerid") String lawyerId) {
        if (acceptRequestService.updateByAccept(lawyerId, acceptRequestId))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_ADD_ACCEPT_REQUEST));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_REQUEST));

    }

}
