package ir.mostashar.model.settingType.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;



@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SettingTypesDTO extends BaseDTO {

    private String uid;
    private String name;
    private String description;
    private Integer type;

    public SettingTypesDTO() {
    }

}
