package ir.mostashar.model.client.controller.v1;

import ir.mostashar.model.file.dto.ListFileDTO;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.repository.LawyerRepository;
import ir.mostashar.model.pack.dto.ListPackDTO;
import ir.mostashar.model.pack.dto.PackForm;
import ir.mostashar.model.pack.service.PackService;
import ir.mostashar.model.request.service.RequestService;
import ir.mostashar.util.Constants;
import ir.mostashar.util.DataUtil;
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
public class PackController {

    @Autowired
    LawyerRepository lawyerRepository;

    @Autowired
    PackService packService;

    @GetMapping(value = "/packs/{lawyerid}", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllPackByLawyer(@PathVariable(value = "lawyerid") String lawyerid) {
        if (!DataUtil.isValidUUID(lawyerid))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ListFileDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_UUID_NOT_VALID));

        Optional<Lawyer> lawyer = lawyerRepository.findByUid(UUID.fromString(lawyerid));
        if (lawyer.isPresent()) {
            Optional<ListPackDTO> allPacks = packService.findAllPacks(lawyer.get().getPricePerMinute());
            if (allPacks.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(allPacks.get());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ListFileDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_LAWYER));
    }

    @PostMapping(value = "/buypack", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> buyPack(@Valid @RequestBody PackForm packForm) {

        return null;
    }

}
