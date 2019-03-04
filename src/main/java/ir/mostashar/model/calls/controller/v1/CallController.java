package ir.mostashar.model.calls.controller.v1;


import ir.mostashar.model.calls.dto.CallDTO;
import ir.mostashar.model.calls.dto.CallForm;
import ir.mostashar.model.calls.dto.ListCallDTO;
import ir.mostashar.model.calls.service.CallService;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/calls")
public class CallController {

    @Autowired
    private CallService callService;

    @PostMapping(value = "/createcall", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createCall(@RequestBody CallForm callForm) {
        if (callService.createCall(callForm)) {
            // محاسبه مدت زمان صحبت با کاربر  و کسر آن از کیف پول و صدو فاکتور
            //
            return ResponseEntity.status(HttpStatus.OK).body(new CallDTO(HttpStatus.OK.value(), Constants.KEY_SUCESSE));
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CallDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_CLIENT_LAWYER));
    }


    @PostMapping(value = "/clientcalls", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllClientCall(@RequestParam("userid") String userid) {
        Optional<ListCallDTO> calls = callService.findListCallDTOByUid(userid, 1);
        if (calls.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(calls.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CallDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_CALL));
    }

    @PostMapping(value = "/lawyercalls", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllLawyerCall(@RequestParam("userid") String userid) {
        Optional<ListCallDTO> calls = callService.findListCallDTOByUid(userid, 2);
        if (calls.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(calls.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CallDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_CALL));
    }

    @PostMapping(value = "/requestcalls", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllRequestCall(@RequestParam("requestid") String requestid) {
        Optional<ListCallDTO> calls = callService.findListCallDTOByUid(requestid, 3);
        if (calls.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(calls.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CallDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_CALL));
    }
}
