package ir.mostashar.model.organization.service;

import ir.mostashar.model.organization.Organization;
import ir.mostashar.model.organization.dto.ListOrganizationDTO;
import ir.mostashar.model.organization.dto.OrganizationDTO;
import ir.mostashar.model.organization.dto.OrganizationForm;
import ir.mostashar.model.organization.repository.OrganizationRepository;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
//            org.setVerified(false);
//            org.setExpiryDate(form.getDescription()); چطوری تعیین میشه
            orgRepository.save(org);
            return true;
        }
        return false;
    }

    public boolean updateOrganization(OrganizationForm form) {
        Optional<Organization> org = orgRepository.findByUsernameAndName(form.getUsername(), form.getName());
        if (org.isPresent()) {
            org.get().setAddress(form.getAddress());
            org.get().setAppStock(form.getAppStock());
            org.get().setCreationDate(System.currentTimeMillis());
            org.get().setDescription(form.getDescription());
            org.get().setName(form.getName());
            org.get().setUsername(form.getUsername());
            org.get().setOrgStock(form.getOrgStock());
            org.get().setTel(form.getTel());
            org.get().setPassword(form.getPassword());
            org.get().setTerminalId(form.getTerminalId());
//            org.setVerified(false);
//            org.setExpiryDate(form.getDescription()); چطوری تعیین میشه
            orgRepository.save(org.get());
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

    public boolean deleteOrg(String orgUid) {
        Optional<Organization> orgOld = orgRepository.findByUid(UUID.fromString(orgUid));
        if (orgOld.isPresent()) {
            orgRepository.delete(orgOld.get());
            return true;
        }
        return false;
    }

    public Optional<Organization> findByUid(String orgUid) {
        Optional<Organization> orgOld = orgRepository.findByUid(UUID.fromString(orgUid));
        if (orgOld.isPresent())
            return orgOld;
        else
            return Optional.empty();
    }

    public Optional<OrganizationDTO> findByVerified(String orgUid, boolean isVerified) {
        Optional<Organization> orgOld = orgRepository.findByUidAndVerified(UUID.fromString(orgUid), isVerified);
        if (orgOld.isPresent()) {
            OrganizationDTO orgDTO = new OrganizationDTO();
            orgDTO.setStatus(HttpStatus.OK.value());
            orgDTO.setMessage(Constants.KEY_SUCESSE);
            orgDTO.setOrgUid(orgOld.get().getUid().toString());
            orgDTO.setName(orgOld.get().getName());
            orgDTO.setDescription(orgOld.get().getDescription());
            orgDTO.setAddress(orgOld.get().getAddress());
            orgDTO.setTel(orgOld.get().getTel());
            orgDTO.setTerminalId(orgOld.get().getTerminalId());
            orgDTO.setUsername(orgOld.get().getUsername());
            orgDTO.setPassword(orgOld.get().getPassword());
            orgDTO.setCreationDate(orgOld.get().getCreationDate());
            orgDTO.setExpiryDate(orgOld.get().getExpiryDate());
            orgDTO.setOrgStock(orgOld.get().getOrgStock());
            orgDTO.setAppStock(orgOld.get().getAppStock());
            return Optional.ofNullable(orgDTO);
        }
        return Optional.empty();
    }

    public Optional<ListOrganizationDTO> findAllVerified(boolean isVerified) {
        Optional<List<Organization>> orgs = orgRepository.findAllByVerified(isVerified);
        if (orgs.isPresent()) {
            ListOrganizationDTO listOrganizationDTO = new ListOrganizationDTO();
            listOrganizationDTO.setStatus(HttpStatus.OK.value());
            listOrganizationDTO.setMessage(Constants.KEY_SUCESSE);

            List<OrganizationDTO> dtoList = new ArrayList<>();
            for (Organization org : orgs.get()) {
                OrganizationDTO orgDTO = new OrganizationDTO();
                orgDTO.setOrgUid(org.getUid().toString());
                orgDTO.setName(org.getName());
                orgDTO.setDescription(org.getDescription());
                orgDTO.setAddress(org.getAddress());
                orgDTO.setTel(org.getTel());
                orgDTO.setTerminalId(org.getTerminalId());
                orgDTO.setUsername(org.getUsername());
                orgDTO.setPassword(org.getPassword());
                orgDTO.setCreationDate(org.getCreationDate());
                orgDTO.setExpiryDate(org.getExpiryDate());
                orgDTO.setOrgStock(org.getOrgStock());
                orgDTO.setAppStock(org.getAppStock());
                dtoList.add(orgDTO);
            }
            listOrganizationDTO.setData(dtoList);
            return Optional.ofNullable(listOrganizationDTO);
        }
        return Optional.empty();
    }

}
