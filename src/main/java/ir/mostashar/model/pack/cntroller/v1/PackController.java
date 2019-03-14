package ir.mostashar.model.pack.cntroller.v1;

import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.BaseDTO;
import ir.mostashar.model.pack.Pack;
import ir.mostashar.model.pack.dto.PackDTO;
import ir.mostashar.model.pack.dto.PackForm;
import ir.mostashar.model.pack.service.PackService;
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
@RequestMapping("/api/v1/pack")
public class PackController {

    @Autowired
    PackService packService;

    /**
     * find exist pack name
     * create pack
     *
     * @param packForm
     * @return
     */
    @ApiOperation(value = "Create Pack", notes ="RequestBody :" + MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping(value = "/createpack", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createPackage(@Valid @RequestBody PackForm packForm) {
        if (!packService.existsPack(packForm.getName())) {
            if (packService.create(packForm)) {
                return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_CREATE_PACK_SUCESSE));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseDTO(HttpStatus.BAD_REQUEST.value(), Constants.KEY_DUPLICATE_PACK));
    }


    @PostMapping(value = "/removepack", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> removePackage(@RequestParam("packid") String packUid) {
        Optional<Pack> pack = packService.findPackByUid(packUid);
        if (pack.isPresent()) {
            if (packService.deletePack(pack.get())) {
                return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_DELETE_PACK));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseDTO(HttpStatus.BAD_REQUEST.value(), Constants.KEY_NOT_FOUND_PACK));
    }

    @PostMapping(value = "/updatepack", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> updatePackage(@Valid @RequestBody PackForm packForm) {
        if (packService.updatePack(packForm))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_UPDATE_PACK_SUCESSE));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseDTO(HttpStatus.BAD_REQUEST.value(), Constants.KEY_NOT_FOUND_PACK));
    }

    @PostMapping(value = "/pack", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findPackByUid(@RequestParam("packid") String packUid) {
        Optional<PackDTO> packDTO = packService.findPackDTOByUid(packUid);
        if (packDTO.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(packDTO.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PackDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_PACK));

    }


}
