package ir.mostashar.model.setting.service;

import ir.mostashar.model.client.service.UserServiceImpl;
import ir.mostashar.model.setting.Setting;
import ir.mostashar.model.setting.dto.ListSettingDTO;
import ir.mostashar.model.setting.dto.SettingDTO;
import ir.mostashar.model.setting.dto.SettingForm;
import ir.mostashar.model.setting.repository.SettingRepo;
import ir.mostashar.model.settingType.SettingType;
import ir.mostashar.model.settingType.service.SettingTypesService;
import ir.mostashar.model.user.User;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SettingService {


    @Autowired
    SettingRepo settingRepo;

    @Autowired
    SettingTypesService settingTypesService;

    @Autowired
    UserServiceImpl userService;

    public boolean createSetting(SettingForm settingForm) {
        Optional<SettingType> settingType = settingTypesService.findSettingTypeByUid(settingForm.getUserUid());
        Optional<User> user = userService.findUserByUid(settingForm.getUserUid());
        if (settingType.isPresent() && user.isPresent()) {
            Optional<Boolean> settingT = settingRepo.existsBySettingType(settingType.get());
            if (settingT.isPresent() && !settingT.get()) {
                Setting setting = new Setting();
                setting.setUid(UUID.randomUUID());
                setting.setDescription(settingForm.getDescription());
                setting.setUserDefined(settingForm.isUserDefined());
                setting.setUser(user.get());
                setting.setSettingType(settingType.get());
                settingRepo.save(setting);
                return true;
            }
        }
        return false;
    }

    public boolean updateSetting(SettingForm settingForm) {
        Optional<Setting> setting = settingRepo.findByUid(UUID.fromString(settingForm.getUid()));
        if (setting.isPresent()) {
            setting.get().setDescription(settingForm.getDescription());
            setting.get().setUserDefined(settingForm.isUserDefined());
            setting.get().setUser(setting.get().getUser());
            setting.get().setSettingType(setting.get().getSettingType());
            settingRepo.save(setting.get());
            return true;
        }
        return false;
    }

    public boolean deleteSetting(String uid) {
        Optional<Setting> setting = settingRepo.findByUid(UUID.fromString(uid));
        if (setting.isPresent()) {
            settingRepo.delete(setting.get());
            return true;
        }
        return false;
    }

    public Optional<Setting> findSettingByUid(String uid) {
        Optional<Setting> setting = settingRepo.findByUid(UUID.fromString(uid));
        if (setting.isPresent())
            return Optional.ofNullable(setting.get());
        else
            return Optional.empty();
    }

    public Optional<SettingDTO> findSettingDTOByUid(String uid) {
        Optional<Setting> setting = settingRepo.findByUid(UUID.fromString(uid));
        if (setting.isPresent()) {
            SettingDTO settingDTO = new SettingDTO();
            settingDTO.setStatus(HttpStatus.OK.value());
            settingDTO.setMessage(Constants.KEY_SUCESSE);

            settingDTO.setUid(setting.get().getUid().toString());
            settingDTO.setDescription(setting.get().getDescription());
            settingDTO.setUserDefined(setting.get().isUserDefined());
            settingDTO.setUserUid(setting.get().getUser().getUid().toString());
            settingDTO.setSettingTypeUid(setting.get().getSettingType().getUid().toString());
            return Optional.ofNullable(settingDTO);
        }
        return Optional.empty();
    }

    public Optional<ListSettingDTO> findAllSettingDTO() {
        List<Setting> list = settingRepo.findAll();
        if (list != null) {
            ListSettingDTO lsDTo = new ListSettingDTO();
            lsDTo.setStatus(HttpStatus.OK.value());
            lsDTo.setMessage(Constants.KEY_SUCESSE);

            List<SettingDTO> dtoList = new ArrayList<>();
            for (Setting setting : list) {
                SettingDTO settingDTO = new SettingDTO();
                settingDTO.setUid(setting.getUid().toString());
                settingDTO.setDescription(setting.getDescription());
                settingDTO.setUserDefined(setting.isUserDefined());
                settingDTO.setUserUid(setting.getUser().getUid().toString());
                settingDTO.setSettingTypeUid(setting.getSettingType().getUid().toString());
                dtoList.add(settingDTO);
            }
            lsDTo.setData(dtoList);
            return Optional.ofNullable(lsDTo);
        }
        return Optional.empty();
    }
}
