package ir.mostashar.model.call.controller.v1;


import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.call.dto.CallDTO;
import ir.mostashar.model.call.dto.CallForm;
import ir.mostashar.model.call.dto.ListCallDTO;
import ir.mostashar.model.call.service.CallService;
import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.service.ClientService;
import ir.mostashar.model.factor.dto.FactorForm;
import ir.mostashar.model.factor.service.FactorService;
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
@RequestMapping("/api/v1/calls")
public class CallController {

    @Autowired
    private CallService callService;

    @Autowired
    private FactorService factorService;

    @Autowired
    private ClientService clientService;

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createCall(@Valid @RequestBody CallForm callForm) {
        if (callService.create(callForm)) {
            Optional<Client> client = clientService.findUserByUid(callForm.getClientId());
            FactorForm factorForm = new FactorForm();
            factorForm.setBillId(callForm.getBillId());
            factorForm.setClientName(client.get().getFirstName() + " " + client.get().getLastName());
            factorForm.setServiceDescription(Constants.KEY_CREATE_CALL_DES);
            // ...

            factorService.create(factorForm);
            // محاسبه مدت زمان صحبت با کاربر  و کسر آن از کیف پول و صدو فاکتور
            //
            return ResponseEntity.status(HttpStatus.OK).body(new CallDTO(HttpStatus.OK.value(), Constants.KEY_SUCESSE));
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CallDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_CLIENT_LAWYER));
    }

    /**
     * find Calls List By client or lawyer or request
     *
     * @param userid
     * @return
     */
    @ApiOperation(value = "Find All List Calls", notes = "type=1,2,3 \n 1=client & 2=lawyer & 3=request")
    @PostMapping(value = "/all", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllClientCall(@RequestParam("type") int type, @RequestParam("userid") String userid) {
        Optional<ListCallDTO> calls = callService.findListCallDTOByUid(type, userid);
        if (calls.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(calls.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CallDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_CALL));
    }


}
