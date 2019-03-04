package ir.mostashar.model.lawyer.controller.v1;

import ir.mostashar.model.acceptRequest.AcceptRequest;
import ir.mostashar.model.acceptRequest.dto.AcceptRequestForm;
import ir.mostashar.model.acceptRequest.service.AcceptRequestService;
import ir.mostashar.model.failRequest.FailRequest;
import ir.mostashar.model.failRequest.dto.FailRequestForm;
import ir.mostashar.model.failRequest.service.FailRequestService;
import ir.mostashar.model.lawyer.dto.LawyerDTO;
import ir.mostashar.model.lawyer.dto.ListLawyerDTO;
import ir.mostashar.model.lawyer.service.LawyerService;
import ir.mostashar.model.request.dto.RequestDTO;
import ir.mostashar.model.request.dto.RequestForm;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/lawyer")
public class LawyerController {


    @Autowired
    LawyerService lawyerService;

    @Autowired
    AcceptRequestService arService;

    @Autowired
    FailRequestService frService;

    @PostMapping(value = "/adddevice", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> addDevice(HttpServletRequest httpRequest) {

        return null;
    }

    @PostMapping(value = "/onlines", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllLawyerOnline() {
        Optional<ListLawyerDTO> list = lawyerService.findListLawyerDTO(2, "", true);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LawyerDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_ONLINE));
    }

    @PostMapping(value = "/online", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> setOnlineLawyer(@RequestParam("lawyerid") String lawyerId, @RequestParam("online") boolean online) {
        if (lawyerService.setOnline(lawyerId, online))
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_SUCESSE));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LawyerDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_LAWYER));
    }

    @PostMapping(value = "/acceptrequest", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> acceptRequest(@Valid @RequestBody AcceptRequestForm arForm) {
        if (arService.createAcceptRequest(arForm))
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_ADD_ACCEPT_REQUEST));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LawyerDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_REQUEST));
    }

    @PostMapping(value = "/failrequest", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> failRequest(@Valid @RequestBody FailRequestForm frForm) {
        if (frService.createFailRequest(frForm))
            return ResponseEntity.status(HttpStatus.OK).body(new LawyerDTO(HttpStatus.OK.value(), Constants.KEY_ADD_FAIL_REQUEST));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LawyerDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_REQUEST));
    }



}
