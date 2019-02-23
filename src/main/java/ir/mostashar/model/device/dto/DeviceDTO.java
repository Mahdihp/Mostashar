package ir.mostashar.model.device.dto;

import ir.mostashar.model.BaseDTO;
import lombok.Data;

@Data
public class DeviceDTO extends BaseDTO {

    private String uid;
    private String imei;
    private String fireBaseRegId;
    private String ipAddress;
    private String model;
    private String useruid;

    public DeviceDTO() {
    }
}
