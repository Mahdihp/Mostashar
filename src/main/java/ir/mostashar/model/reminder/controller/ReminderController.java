package ir.mostashar.model.reminder.controller;


import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.reminder.dto.ListReminderDTO;
import ir.mostashar.model.reminder.dto.ReminderDTO;
import ir.mostashar.model.reminder.service.ReminderService;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/reminders")
public class ReminderController {

    @Autowired
    ReminderService reminderService;

    @ApiOperation(value = "Find All Notif Lawyer", notes = "RequestBody :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/all", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> updateWallet(@RequestParam("lawyerid") String lawyerUid) {
        Optional<ListReminderDTO> list = reminderService.findAllDTO(2,lawyerUid);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new ReminderDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_REMINDERS));
    }


}
