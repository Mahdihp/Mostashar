package ir.mostashar.model.client.controller.v1;

import ir.mostashar.model.client.dto.ClientDTO;
import ir.mostashar.model.client.dto.ClientProfileForm;
import ir.mostashar.model.client.service.ClientService;
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
@RequestMapping("/api/v1/clients")
public class ProfileClientController {

    @Autowired
    ClientService clientService;

    @PostMapping(value = "/profile", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> updateProfile(@Valid @RequestBody ClientProfileForm cpForm) {
        if (clientService.updateClient(cpForm))
            return ResponseEntity.status(HttpStatus.OK).body(new ClientDTO(HttpStatus.OK.value(), Constants.KEY_UPDATE_PROFILE));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new ClientDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_USER));
    }

    @PostMapping(value = "/profile/", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findClientProfile(@RequestParam("clientid") String clientId) {
        Optional<ClientDTO> clientDTO = clientService.findClientDTOByUid(clientId);
        if (clientDTO.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(clientDTO.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new ClientDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_USER));
    }
}