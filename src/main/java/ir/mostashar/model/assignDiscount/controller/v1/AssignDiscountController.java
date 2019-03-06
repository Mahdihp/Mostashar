package ir.mostashar.model.assignDiscount.controller.v1;


import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.assignDiscount.dto.AssignDiscountDTO;
import ir.mostashar.model.assignDiscount.dto.AssignDiscountForm;
import ir.mostashar.model.assignDiscount.dto.ListAssignDiscountDTO;
import ir.mostashar.model.assignDiscount.service.AssignDiscountService;
import ir.mostashar.model.file.dto.FileDTO;
import ir.mostashar.model.wallet.dto.WalletDTO;
import ir.mostashar.model.wallet.dto.WalletForm;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/assigndiscounts")
public class AssignDiscountController {

    @Autowired
    AssignDiscountService adService;


    @ApiOperation(value = "Create AssignDiscount", notes = "RequestBody :" + MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> updateWallet(@Valid @RequestBody AssignDiscountForm adForm) {
        if (adService.createAssignDiscount(adForm))
            return ResponseEntity.status(HttpStatus.OK).body(new AssignDiscountDTO(HttpStatus.OK.value(), Constants.KEY_CREATE_ASSIGN_DISCOUNT));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new AssignDiscountDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_LAWYER));

    }

    @ApiOperation(value = "Find All AssignDiscount", notes = "RequestBody :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/all", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAll(@RequestParam("lawyerid") String lawyerUid) {
        Optional<ListAssignDiscountDTO> list = adService.findAllDTOByUid(3, lawyerUid);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new AssignDiscountDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_LAWYER));
    }

    @ApiOperation(value = "Active AssignDiscount", notes = "RequestBody :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/active", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> activeAssignDiscount(@RequestParam("lawyerid") String lawyerUid, @RequestParam("assigndiscountid") String assigndiscountuid) {
        if (adService.activeAssignDiscount(assigndiscountuid, lawyerUid, true))
            return ResponseEntity.status(HttpStatus.OK).body(new AssignDiscountDTO(HttpStatus.OK.value(), Constants.KEY_ACTIVEED));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new AssignDiscountDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_LAWYER));
    }

    @ApiOperation(value = "DiActive AssignDiscount", notes = "RequestBody :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/diactive", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> diactiveAssignDiscount(@RequestParam("lawyerid") String lawyerUid, @RequestParam("assigndiscountid") String assigndiscountuid) {
        if (adService.activeAssignDiscount(assigndiscountuid, lawyerUid, false))
            return ResponseEntity.status(HttpStatus.OK).body(new AssignDiscountDTO(HttpStatus.OK.value(), Constants.KEY_DIACTIVE));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new AssignDiscountDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_LAWYER));
    }


}
