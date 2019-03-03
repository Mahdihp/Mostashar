package ir.mostashar.model.client.controller.v1;

import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.repository.LawyerRepo;
import ir.mostashar.model.pack.BuyPackStatus;
import ir.mostashar.model.pack.dto.BuyPackDTO;
import ir.mostashar.model.pack.dto.BuyPackForm;
import ir.mostashar.model.pack.dto.ListPackDTO;
import ir.mostashar.model.pack.dto.PackDTO;
import ir.mostashar.model.pack.service.PackService;
import ir.mostashar.utils.Constants;
import ir.mostashar.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/client")
public class ClientController {


    @Autowired
    LawyerRepo lawyerRepo;

    @Autowired
    PackService packService;



    @PostMapping(value = "/packs", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllPackByLawyer(@RequestParam("lawyerid") String lawyerid) {
        if (!DataUtil.isValidUUID(lawyerid))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PackDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_UUID_NOT_VALID));

        Optional<Lawyer> lawyer = lawyerRepo.findByUid(UUID.fromString(lawyerid));
        if (lawyer.isPresent()) {
            Optional<ListPackDTO> allPacks = packService.findAllPacks(lawyer.get().getPricePerMinute());
            if (allPacks.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(allPacks.get());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PackDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_LAWYER));
    }

    @PostMapping(value = "/buypack", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> buyPack(@Valid @RequestBody BuyPackForm buyPackForm) {
        Optional<BuyPackStatus> buyPack = packService.createBuyPack(buyPackForm);
        if (buyPack.isPresent()) {
            switch (buyPack.get().getBuyPack()) {
                case ComplateAll:
                    return ResponseEntity.status(HttpStatus.OK).body(new BuyPackDTO(HttpStatus.OK.value(), Constants.KEY_SUCESSE));
                case ErrorAll:
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PackDTO(HttpStatus.BAD_REQUEST.value(), Constants.KEY_FAIL));
                case ConsumptionPackError:
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PackDTO(HttpStatus.BAD_REQUEST.value(), Constants.KEY_FAIL_CONSUMPTIONPACK));
                case PackSnapshotError:
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PackDTO(HttpStatus.BAD_REQUEST.value(), Constants.KEY_FAIL));
//                case FactorError:
//                    return ResponseEntity.status(HttpStatus.OK).body(new PackDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_FAIL_FACTOR));
            }
        }
        return null;
    }

    // درخواست اعمال کد تخفیف
    // Update Scsore per peyment price User.



}
