package ir.mostashar.model.client.controller.v1;

import ir.mostashar.model.acceptRequest.dto.ListAcceptRequestDTO;
import ir.mostashar.model.acceptRequest.service.AcceptRequestService;
import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.dto.ClientDTO;
import ir.mostashar.model.client.dto.ClientProfileForm;
import ir.mostashar.model.client.service.ClientService;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.repository.LawyerRepo;
import ir.mostashar.model.notification.Notification;
import ir.mostashar.model.notification.service.NotificationService;
import ir.mostashar.model.pack.BuyPackStatus;
import ir.mostashar.model.pack.dto.BuyPackDTO;
import ir.mostashar.model.pack.dto.BuyPackForm;
import ir.mostashar.model.pack.dto.ListPackDTO;
import ir.mostashar.model.pack.dto.PackDTO;
import ir.mostashar.model.pack.service.PackService;
import ir.mostashar.model.reminder.dto.ListReminderDTO;
import ir.mostashar.model.reminder.service.ReminderService;
import ir.mostashar.model.request.RequestStatus;
import ir.mostashar.model.request.service.RequestService;
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
    public ResponseEntity<?> findClientProfile(@RequestParam("userid") String userUid) {
        Optional<ClientDTO> clientDTO = clientService.findClientDTOByUid(userUid);
        if (clientDTO.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(clientDTO.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new ClientDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_USER));
    }
}