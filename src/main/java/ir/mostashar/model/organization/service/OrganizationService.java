package ir.mostashar.model.organization.service;

import ir.mostashar.model.organization.Organization;
import ir.mostashar.model.organization.dto.ListOrganizationDTO;
import ir.mostashar.model.organization.dto.OrganizationDTO;
import ir.mostashar.model.organization.dto.OrganizationForm;
import ir.mostashar.model.organization.repository.OrganizationRepo;
import ir.mostashar.model.wallet.service.WalletService;
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
    OrganizationRepo orgRepository;

    @Autowired
    WalletService walletService;

    public UUID createOrganization(OrganizationForm form) {
        Optional<Organization> orgOld = orgRepository.findByUsernameOrName(form.getUsername(), form.getName());
        UUID uuid;
        if (!orgOld.isPresent()) {
            Organization org = new Organization();
            uuid = UUID.randomUUID();
            org.setUid(uuid);
            org.setAddress(form.getAddress());
            org.setPercentMasterStock(form.getPercentMasterStock());
            org.setCreationDate(System.currentTimeMillis());
            org.setDescription(form.getDescription());
            org.setName(form.getName());
            org.setUsername(form.getUsername());
            org.setPercentOrgStock(form.getPercentOrgStock());
            org.setTel(form.getTel());
            org.setPassword(form.getPassword());
            org.setTerminalId(form.getTerminalId());
            org.setVerified(false);
            org.setExpiryDate(3L);// چطوری تعیین میشه
            orgRepository.save(org);
            return uuid;
        }
        return null;
    }



    public boolean updateOrganization(OrganizationForm form) {
        Optional<Organization> org = orgRepository.findByUsernameOrName(form.getUsername(), form.getName());
        if (org.isPresent()) {
            org.get().setAddress(form.getAddress());
            org.get().setPercentMasterStock(form.getPercentMasterStock());
            org.get().setCreationDate(System.currentTimeMillis());
            org.get().setDescription(form.getDescription());
            org.get().setName(form.getName());
            org.get().setUsername(form.getUsername());
            org.get().setPercentOrgStock(form.getPercentOrgStock());
            org.get().setTel(form.getTel());
            org.get().setPassword(form.getPassword());
            org.get().setTerminalId(form.getTerminalId());
//            org.get().setVerified(form.());
            org.get().setExpiryDate(form.getExpiryDate()); //چطوری تعیین میشه
            orgRepository.save(org.get());
            return true;
        }
        return false;
    }

    public boolean verifiedOrganization(String orgUid, String userName, String password) {
        Optional<Organization> orgOld = orgRepository.findByUidAndUsernameAndPassword(UUID.fromString(orgUid), userName, password);
        if (orgOld.isPresent()) {
            orgOld.get().setVerified(true);
            orgRepository.save(orgOld.get());
            return true;
        }
        return false;
    }

    public boolean unVerifiedOrganization(String orgUid, String userName, String password) {
        Optional<Organization> orgOld = orgRepository.findByUidAndUsernameAndPassword(UUID.fromString(orgUid), userName, password);
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

    public Optional<OrganizationDTO> findByUidAndUsername(String orgUid, String username) {
        Optional<Organization> orgOld = orgRepository.findByUidAndUsername(UUID.fromString(orgUid), username);
        if (orgOld.isPresent()) {
            OrganizationDTO orgDTO = new OrganizationDTO();
            orgDTO.setStatus(HttpStatus.OK.value());
            orgDTO.setMessage(Constants.KEY_SUCESSE);
            orgDTO.setOrgId(orgOld.get().getUid().toString());
            orgDTO.setName(orgOld.get().getName());
            orgDTO.setDescription(orgOld.get().getDescription());
            orgDTO.setAddress(orgOld.get().getAddress());
            orgDTO.setTel(orgOld.get().getTel());
            orgDTO.setTerminalId(orgOld.get().getTerminalId());
            orgDTO.setUsername(orgOld.get().getUsername());
            orgDTO.setPassword(orgOld.get().getPassword());
            orgDTO.setCreationDate(orgOld.get().getCreationDate());
            orgDTO.setExpiryDate(orgOld.get().getExpiryDate());
            orgDTO.setPercentMasterStock(orgOld.get().getPercentMasterStock());
            orgDTO.setPercentOrgStock(orgOld.get().getPercentOrgStock());
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
                orgDTO.setOrgId(org.getUid().toString());
                orgDTO.setName(org.getName());
                orgDTO.setDescription(org.getDescription());
                orgDTO.setAddress(org.getAddress());
                orgDTO.setTel(org.getTel());
                orgDTO.setTerminalId(org.getTerminalId());
                orgDTO.setUsername(org.getUsername());
                orgDTO.setPassword(org.getPassword());
                orgDTO.setCreationDate(org.getCreationDate());
                orgDTO.setExpiryDate(org.getExpiryDate());
                orgDTO.setPercentMasterStock(org.getPercentMasterStock());
                orgDTO.setPercentOrgStock(org.getPercentOrgStock());
                dtoList.add(orgDTO);
            }
            listOrganizationDTO.setData(dtoList);
            return Optional.ofNullable(listOrganizationDTO);
        }
        return Optional.empty();
    }

}
