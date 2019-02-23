package ir.mostashar.model.setting.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListSettingDTO extends BaseDTO {

    private List<SettingDTO> data;

    public ListSettingDTO() {
    }
}
