package ir.mostashar.model.device.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListDeviceDTO extends BaseDTO {

    private List<DeviceDTO> data;

    public ListDeviceDTO() {
    }
}
