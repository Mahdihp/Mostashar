package ir.mostashar.model.adminConfirmation.controller;


import ir.mostashar.model.adminConfirmation.dto.ACDTO;
import ir.mostashar.model.adminConfirmation.dto.ListACDTO;
import ir.mostashar.model.adminConfirmation.service.AdminConfirmationservice;
import ir.mostashar.model.bill.dto.BillForm;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.soap.Addressing;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/adminconfirm")
public class ACController {

    @Autowired
    AdminConfirmationservice acService;

    @PostMapping(value = "/adminconfirms", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createConfirm(@RequestParam("type") int type, @RequestParam("userid") String userUid, @RequestParam("isdeleted") boolean isDeleted) {
        Optional<ListACDTO> list = acService.findAllDTO(type, userUid, isDeleted);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new ACDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_AC));
    }

}
