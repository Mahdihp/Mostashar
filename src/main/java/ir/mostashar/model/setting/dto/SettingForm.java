package ir.mostashar.model.setting.dto;

import lombok.Data;

@Data
public class SettingForm {

    private String uid;

    private String description;

    private boolean userDefined = false;

    private String userUid;

    private String settingTypeUid;

    public SettingForm() {
    }
}
