package ir.mostashar.model.setting.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SettingDTO extends BaseDTO {

    private String uid;
    private String description;
    private Boolean userDefined = false;
    private String userUid;
    private String settingTypeUid;

    public SettingDTO() {
    }
}
