package ir.mostashar.model.settingType.service;

import ir.mostashar.model.settingType.SettingType;
import ir.mostashar.model.settingType.dto.ListSettingTypesDTO;
import ir.mostashar.model.settingType.dto.SettingTypesDTO;
import ir.mostashar.model.settingType.dto.SettingTypesForm;
import ir.mostashar.model.settingType.repository.SettingTypesRepo;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SettingTypesService {

    @Autowired
    SettingTypesRepo settingTypesRepo;

    /**
     * Check Duplicate Name And Later Save New Object
     *
     * @param stForm
     * @return true & false
     */
    public boolean createSettingTypes(SettingTypesForm stForm) {
        Optional<Boolean> name = settingTypesRepo.existsByName(stForm.getName());
        if (name.isPresent()) {
            SettingType settingType = new SettingType();
            settingType.setUid(UUID.randomUUID());
            settingType.setName(stForm.getName());
            settingType.setDescription(stForm.getDescription());
            settingType.setType(stForm.getType());
            settingTypesRepo.save(settingType);
            return true;
        }
        return false;
    }

    public boolean updateSettingTypes(SettingTypesForm stForm) {
        Optional<SettingType> settingTypes = settingTypesRepo.findByUid(UUID.fromString(stForm.getUid()));
        if (settingTypes.isPresent()) {
            settingTypes.get().setName(stForm.getName());
            settingTypes.get().setDescription(stForm.getDescription());
            settingTypes.get().setType(stForm.getType());
            settingTypesRepo.save(settingTypes.get());
            return true;
        }
        return false;
    }

    public boolean deleteSettingTypes(String uid) {
        Optional<SettingType> settingTypes = settingTypesRepo.findByUid(UUID.fromString(uid));
        if (settingTypes.isPresent()) {
            settingTypesRepo.delete(settingTypes.get());
            return true;
        }
        return false;
    }
    public Optional<SettingType> findSettingTypeByUid(String uid) {
        Optional<SettingType> settingTypes = settingTypesRepo.findByUid(UUID.fromString(uid));
        if (settingTypes.isPresent()) {
            SettingType stDTO = new SettingType();

            stDTO.setUid(settingTypes.get().getUid());
            stDTO.setName(settingTypes.get().getName());
            stDTO.setDescription(settingTypes.get().getDescription());
            stDTO.setType(settingTypes.get().getType());
            return Optional.ofNullable(stDTO);
        }
        return Optional.empty();
    }

    public Optional<SettingTypesDTO> findSettingTypeDTOByUid(String uid) {
        Optional<SettingType> settingTypes = settingTypesRepo.findByUid(UUID.fromString(uid));
        if (settingTypes.isPresent()) {
            SettingTypesDTO stDTO = new SettingTypesDTO();
            stDTO.setStatus(HttpStatus.OK.value());
            stDTO.setMessage(Constants.KEY_SUCESSE);

            stDTO.setUid(settingTypes.get().getUid().toString());
            stDTO.setName(settingTypes.get().getName());
            stDTO.setDescription(settingTypes.get().getDescription());
            stDTO.setType(settingTypes.get().getType());
            return Optional.ofNullable(stDTO);
        }
        return Optional.empty();
    }

    public Optional<SettingTypesDTO> findSettingTypeDTOByName(String name) {
        Optional<SettingType> settingTypes = settingTypesRepo.findByName(name);
        if (settingTypes.isPresent()) {
            SettingTypesDTO stDTO = new SettingTypesDTO();
            stDTO.setStatus(HttpStatus.OK.value());
            stDTO.setMessage(Constants.KEY_SUCESSE);

            stDTO.setUid(settingTypes.get().getUid().toString());
            stDTO.setName(settingTypes.get().getName());
            stDTO.setDescription(settingTypes.get().getDescription());
            stDTO.setType(settingTypes.get().getType());
            return Optional.ofNullable(stDTO);
        }
        return Optional.empty();
    }

    public Optional<SettingTypesDTO> findSettingTypeDTOByType(short type) {
        Optional<SettingType> settingTypes = settingTypesRepo.findByType(type);
        if (settingTypes.isPresent()) {
            SettingTypesDTO stDTO = new SettingTypesDTO();
            stDTO.setStatus(HttpStatus.OK.value());
            stDTO.setMessage(Constants.KEY_SUCESSE);

            stDTO.setUid(settingTypes.get().getUid().toString());
            stDTO.setName(settingTypes.get().getName());
            stDTO.setDescription(settingTypes.get().getDescription());
            stDTO.setType(settingTypes.get().getType());
            return Optional.ofNullable(stDTO);
        }
        return Optional.empty();
    }

    public Optional<ListSettingTypesDTO> findAllSettingTypeDTO() {
        List<SettingType> settingTypeList = settingTypesRepo.findAll();
        if (settingTypeList != null) {
            ListSettingTypesDTO lstDTo = new ListSettingTypesDTO();
            lstDTo.setStatus(HttpStatus.OK.value());
            lstDTo.setMessage(Constants.KEY_SUCESSE);

            List<SettingTypesDTO> dtoList = new ArrayList<>();
            for (SettingType settingType : settingTypeList) {
                SettingTypesDTO stDTO = new SettingTypesDTO();
                stDTO.setUid(settingType.getUid().toString());
                stDTO.setName(settingType.getName());
                stDTO.setDescription(settingType.getDescription());
                stDTO.setType(settingType.getType());
                dtoList.add(stDTO);
            }
            lstDTo.setData(dtoList);
            return Optional.ofNullable(lstDTo);
        }
        return Optional.empty();
    }

}
