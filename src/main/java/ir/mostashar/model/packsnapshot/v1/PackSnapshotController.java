package ir.mostashar.model.packsnapshot.v1;

import ir.mostashar.model.BaseDTO;
import ir.mostashar.model.pack.service.PackService;
import ir.mostashar.model.packsnapshot.dto.PackSnapshotForm;
import ir.mostashar.model.packsnapshot.service.PackSnapshotService;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
// ایا وجود کنترلر برای این جدول نیاز هست؟
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/packsnapshot")
public class PackSnapshotController {

    @Autowired
    PackSnapshotService packsnapshotService;

    @Autowired
    PackService packService;

    @PostMapping(value = "/createpacksnapshot", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createPackSnapShot(@Valid @RequestBody PackSnapshotForm packForm) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseDTO(HttpStatus.BAD_REQUEST.value(), Constants.KEY_DUPLICATE_PACK));
    }


}
