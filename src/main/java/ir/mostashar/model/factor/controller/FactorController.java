package ir.mostashar.model.factor.controller;


import ir.mostashar.model.factor.dto.FactorDTO;
import ir.mostashar.model.factor.dto.ListFactorDTO;
import ir.mostashar.model.factor.service.FactorService;
import ir.mostashar.model.pack.dto.PackDTO;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/factor")
public class FactorController {

    @Autowired
    private FactorService factorService;


    @PostMapping(value = "/userfactors", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllUserFactor(@RequestParam("userid") String userid) {
//        Optional<ListFactorDTO> factors = factorService.fi.findListFactorDTOByUserUid(userid);
//        if (factors.isPresent()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(factors.get());
//        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new FactorDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_USER));
    }


}
