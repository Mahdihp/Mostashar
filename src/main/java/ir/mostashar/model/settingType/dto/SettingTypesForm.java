package ir.mostashar.model.settingType.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SettingTypesForm {


    private String uid;

    @NotBlank
    private String name;
    private String description;

    @NotBlank
    private short type;


    public SettingTypesForm() {
    }
}
