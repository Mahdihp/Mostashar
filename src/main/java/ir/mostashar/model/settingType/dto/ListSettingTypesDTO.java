package ir.mostashar.model.settingType.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListSettingTypesDTO extends BaseDTO {

    private List<SettingTypesDTO> data;

    public ListSettingTypesDTO() {
    }
}
