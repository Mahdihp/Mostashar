package ir.mostashar.model.pack.cntroller.v1;

import ir.mostashar.model.BaseDTO;
import ir.mostashar.model.pack.Pack;
import ir.mostashar.model.pack.dto.PackDTO;
import ir.mostashar.model.pack.dto.PackForm;
import ir.mostashar.model.pack.service.PackService;
import ir.mostashar.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/packs")
public class PackageController {

    @Autowired
    PackService packService;

    /**
     * find exist pack name
     * create pack
     *
     * @param packForm
     * @return
     */
    @PostMapping(value = "/createpackage", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createPackage(@Valid @RequestBody PackForm packForm) {
        if (!packService.existsPack(packForm.getName())) {
            if (packService.createPack(packForm)) {
                return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_CREATE_PACK_SUCESSE));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseDTO(HttpStatus.BAD_REQUEST.value(), Constants.KEY_DUPLICATE_PACK));
    }

    @PostMapping(value = "/removepackage{uid}", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> removePackage(@PathVariable(value = "uid") String uid) {
        Optional<Pack> pack = packService.findPackByUid(uid);
        if (pack.isPresent()) {
            if (packService.deletePack(pack.get())) {
                return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_DELETE_PACK));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseDTO(HttpStatus.BAD_REQUEST.value(), Constants.KEY_NOT_FOUND_PACK));
    }

    @PostMapping(value = "/updatepackage", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> updatePackage(@Valid @RequestBody PackForm packForm) {
        if (packService.updatePack(packForm))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_UPDATE_PACK_SUCESSE));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseDTO(HttpStatus.BAD_REQUEST.value(), Constants.KEY_NOT_FOUND_PACK));
    }

    @PostMapping(value = "/{uid}", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findPackByUid(@PathVariable(value = "uid") String uid) {
        Optional<PackDTO> packDTO = packService.findPackDTOByUid(uid);
        if (packDTO.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(packDTO.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PackDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_PACK));

    }


}
