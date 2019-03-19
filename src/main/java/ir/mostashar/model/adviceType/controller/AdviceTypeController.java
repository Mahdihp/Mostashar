package ir.mostashar.model.adviceType.controller;

import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.BaseDTO;
import ir.mostashar.model.adviceType.dto.ListAdviceTypeDTO;
import ir.mostashar.model.adviceType.service.AdviceTypeService;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/19/19
 * Time: 11:30
 * https://github.com/mahdihp
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/advicetypes")
public class AdviceTypeController {
    @Autowired
    private AdviceTypeService adviceTypeService;

    @ApiOperation(value = "Get List All Parent Advice type")
    @PostMapping(value = "/all", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllParentAdviceTypes(@RequestParam("isparent") boolean isParent, @RequestParam("parentid") int parentId) {
        Optional<ListAdviceTypeDTO> list = adviceTypeService.findAllDTO(isParent, parentId);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_ADVICE_TYPE));
    }

}
