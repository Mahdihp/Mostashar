package ir.mostashar.model.assignDiscount.controller.v1;


import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.assignDiscount.dto.AssignDiscountDTO;
import ir.mostashar.model.assignDiscount.service.AssignDiscountService;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/assigndiscounts")
public class AssignDiscountController {

    @Autowired
    AssignDiscountService adService;

    @ApiOperation(value = "Add AssignDiscount Code Off", notes = "RequestBody :" + MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping(value = "/add", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createAssignDiscount(@RequestParam("clientId") String userId, @RequestParam("codeoff") String codeOff) {
        if (adService.createAssignDiscount(userId, codeOff))
            return ResponseEntity.status(HttpStatus.OK).body(new AssignDiscountDTO(HttpStatus.OK.value(), Constants.KEY_CREATE_ASSIGN_DISCOUNT));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new AssignDiscountDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_LAWYER));

    }

    /*@ApiOperation(value = "Find All AssignDiscount By User", notes = "RequestBody :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/all", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAll(@RequestParam("userid") String userid) {
        Optional<ListAssignDiscountDTO> list = adService.findAllDTOByUid(userid);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new AssignDiscountDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_LAWYER));
    }*/




}
