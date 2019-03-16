package ir.mostashar.model.discountPack.controller.v1;


import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.discountPack.dto.DiscountPackDTO;
import ir.mostashar.model.discountPack.dto.DiscountPackForm;
import ir.mostashar.model.discountPack.dto.ListDiscountPackDTO;
import ir.mostashar.model.discountPack.service.DiscountPackService;
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
@RequestMapping("/api/v1/discountpacks")
public class DiscountPackController {


    @Autowired
    DiscountPackService dpService;


    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createDiscountPack(@Valid @RequestBody DiscountPackForm dpForm) {
        dpService.create(dpForm);
        return ResponseEntity.status(HttpStatus.OK).body(new DiscountPackDTO(HttpStatus.OK.value(), Constants.KEY_SUCESSE));
    }

    @PostMapping(value = "/update", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> updateDiscountPack(@Valid @RequestBody DiscountPackForm dpForm) {
        dpService.update(dpForm);
        return ResponseEntity.status(HttpStatus.OK).body(new DiscountPackDTO(HttpStatus.OK.value(), Constants.KEY_SUCESSE));
    }

    @ApiOperation(value = "Find DiscountPack By title", notes = "RequestBody :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/all", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findDiscountPack(@RequestParam("title") String title) {
        Optional<ListDiscountPackDTO> list = dpService.findAllDTO(title);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new DiscountPackDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_LAWYER));
    }

    @ApiOperation(value = "Find DiscountPack", notes = "typeUser=1 find by title" + "\n" + "typeUser=2 find by code" + "\n" + "RequestBody :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findDiscountPackByUid(@RequestParam("discountpackid") String discountpackid) {
        Optional<DiscountPackDTO> discountPack = dpService.findDTOByUid(discountpackid);
        if (discountPack.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(discountPack.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new DiscountPackDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_LAWYER));
    }

    @ApiOperation(value = "Active DiscountPack", notes = "RequestBody :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/active", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> activeDiscountPack(@RequestParam("discountpackid") String discountpackid) {
        if (dpService.activeDiscount(discountpackid,  true))
            return ResponseEntity.status(HttpStatus.OK).body(new DiscountPackDTO(HttpStatus.OK.value(), Constants.KEY_ACTIVEED));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new DiscountPackDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_LAWYER));
    }

    @ApiOperation(value = "DiActive DiscountPack", notes = "RequestBody :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/diactive", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> diactiveDiscountPack(@RequestParam("discountpackid") String discountpackid) {
        if (dpService.activeDiscount(discountpackid, false))
            return ResponseEntity.status(HttpStatus.OK).body(new DiscountPackDTO(HttpStatus.OK.value(), Constants.KEY_DIACTIVE));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new DiscountPackDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_LAWYER));
    }

}
