package ir.mostashar.model.organization.service;

import ir.mostashar.model.organization.Organization;
import ir.mostashar.model.organization.dto.OrganizationForm;
import ir.mostashar.model.organization.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository orgRepository;

    public boolean createOrganization(OrganizationForm form) {
        Optional<Organization> orgOld = orgRepository.findByUsernameAndName(form.getUsername(), form.getName());
        if (!orgOld.isPresent()) {
            Organization org = new Organization();
            org.setUid(UUID.randomUUID());
            org.setAddress(form.getAddress());
            org.setAppStock(form.getAppStock());
            org.setCreationDate(System.currentTimeMillis());
            org.setDescription(form.getDescription());
            org.setName(form.getName());
            org.setUsername(form.getUsername());
            org.setOrgStock(form.getOrgStock());
            org.setTel(form.getTel());
            org.setPassword(form.getPassword());
            org.setTerminalId(form.getTerminalId());
            org.setVerified(false);
//            org.setExpiryDate(form.getDescription()); چطوری تعیین میشه
            orgRepository.save(org);
            return true;
        }
        return false;
    }

    public boolean setVerifiedOrganization(String orgUid) {
        Optional<Organization> orgOld = orgRepository.findByUidAndVerified(UUID.fromString(orgUid), false);
        if (orgOld.isPresent()) {
            orgOld.get().setVerified(true);
            orgRepository.save(orgOld.get());
            return true;
        }
        return false;
    }
    public boolean deleteVerifiedOrganization(String orgUid) {
        Optional<Organization> orgOld = orgRepository.findByUidAndVerified(UUID.fromString(orgUid), true);
        if (orgOld.isPresent()) {
            orgOld.get().setVerified(false);
            orgRepository.save(orgOld.get());
            return true;
        }
        return false;
    }


    public boolean updateOrganization(OrganizationForm form) {
        return false;
    }
}
