package ir.mostashar.model.packsnapshot.v1;

import ir.mostashar.model.BaseDTO;
import ir.mostashar.model.packsnapshot.dto.PackSnapshotDTO;
import ir.mostashar.model.packsnapshot.dto.PackForm;
import ir.mostashar.model.packsnapshot.service.PackSnapshotService;
import ir.mostashar.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/packs")
public class PackSnapshotController {

    @Autowired
    PackSnapshotService packsnapshotService;

    /**
     * find exist pack name
     * create pack
     *
     * @param packForm
     * @return
     */
    @PostMapping(value = "/createpackage", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> createPackage(@Valid @RequestBody PackForm packForm) {
        if (!packService.existsPack(packForm.getName())) {
            if (packService.createPack(packForm)) {
                return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value() + "", Constants.KEY_CREATE_PACK_SUCESSE));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseDTO(HttpStatus.BAD_REQUEST.value() + "", Constants.KEY_DUPLICATE_PACK));
    }

    @PostMapping(value = "/removepackage{uid}", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> removePackage(@PathVariable(value = "uid") String uid) {
        if (packService.deletePack(uid))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value() + "", Constants.KEY_DELETE_PACK));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseDTO(HttpStatus.BAD_REQUEST.value() + "", Constants.KEY_DELETE_PACK_FAILED));
    }

    @PostMapping(value = "/updatepackage", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> updatePackage(@Valid @RequestBody PackForm packForm) {
        if (packService.updatePack(packForm))
            return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value() + "", Constants.KEY_UPDATE_PACK_SUCESSE));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseDTO(HttpStatus.BAD_REQUEST.value() + "", Constants.KEY_NOT_FOUND_PACK));
    }

    @PostMapping(value = "/{uid}", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity<?> findPackByUid(@PathVariable(value = "uid") String uid) {
        Optional<PackSnapshotDTO> packDTO = packService.findPackDTOByUid(uid);
        if (packDTO.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(packDTO.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PackSnapshotDTO("404", Constants.KEY_NOT_FOUND_PACK));

    }


}
