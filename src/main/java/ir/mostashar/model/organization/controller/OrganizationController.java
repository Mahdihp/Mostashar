package ir.mostashar.model.organization.controller;


import ir.mostashar.model.factor.dto.FactorDTO;
import ir.mostashar.model.factor.dto.FactorForm;
import ir.mostashar.model.organization.dto.OrganizationDTO;
import ir.mostashar.model.organization.dto.OrganizationForm;
import ir.mostashar.model.organization.service.OrganizationService;
import ir.mostashar.model.wallet.dto.WalletForm;
import ir.mostashar.model.wallet.service.WalletService;
import ir.mostashar.utils.Constants;
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
@RequestMapping("/api/v1/organizations")
public class OrganizationController {

    @Autowired
    OrganizationService orgService;

    @Autowired
    WalletService walletService;


    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createOrgs(@Valid @RequestBody OrganizationForm orgForm) {
        UUID uid = orgService.createOrganization(orgForm);
        if (uid != null)
            return ResponseEntity.status(HttpStatus.OK).body(new OrganizationDTO(HttpStatus.OK.value(), Constants.KEY_ADD_ORG, uid.toString()));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new OrganizationDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_FAIL));
    }

    @PostMapping(value = "/update", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> updateOrgs(@Valid @RequestBody OrganizationForm orgForm) {
        if (orgService.updateOrganization(orgForm))
            return ResponseEntity.status(HttpStatus.OK).body(new OrganizationDTO(HttpStatus.OK.value(), Constants.KEY_UPDATE_ORG));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new OrganizationDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_ORG));
    }

    @PostMapping(value = "/verified", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> verifiedOrgs(@RequestParam("orgid") String orgId, @RequestParam("username") String userName, @RequestParam("password") String password) {
        if (orgService.verifiedOrganization(orgId, userName, password))
            return ResponseEntity.status(HttpStatus.OK).body(new OrganizationDTO(HttpStatus.OK.value(), Constants.KEY_VERIFIED_ORG));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new OrganizationDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_ORG));

    }

    @PostMapping(value = "/unverified", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> unVerifiedOrgs(@RequestParam("orgid") String orgId, @RequestParam("username") String userName, @RequestParam("password") String password) {
        if (orgService.unVerifiedOrganization(orgId, userName, password))
            return ResponseEntity.status(HttpStatus.OK).body(new OrganizationDTO(HttpStatus.OK.value(), Constants.KEY_UNVERIFIED_ORG));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new OrganizationDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_ORG));

    }

    @PostMapping(value = "/orginfo", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> orgsInfo(@RequestParam("orgid") String orgId, @RequestParam("username") String userName) {
        Optional<OrganizationDTO> org = orgService.findByUidAndUsername(orgId, userName);
        if (org.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(org.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new OrganizationDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_ORG));

    }

    @PostMapping(value = "/createwalletorg", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createWalletOrg(@Valid @RequestBody WalletForm walletForm) {
        UUID uid = walletService.createOrgWallet(walletForm);
        if (uid != null) {
            OrganizationDTO orgDTo = new OrganizationDTO(HttpStatus.OK.value(), Constants.KEY_SUCESSE);
            orgDTo.setWalletId(uid.toString());
            return ResponseEntity.status(HttpStatus.OK).body(orgDTo);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new OrganizationDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_ORG));
    }


}
