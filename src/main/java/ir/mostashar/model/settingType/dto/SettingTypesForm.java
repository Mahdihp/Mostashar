package ir.mostashar.model.settingType.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SettingTypesForm {


    private String uid;

    @NotNull
    private String name;
    private String description;

    @NotNull
    private Integer type;


    public SettingTypesForm() {
    }
}
