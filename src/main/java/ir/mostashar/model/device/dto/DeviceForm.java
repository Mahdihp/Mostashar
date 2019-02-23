package ir.mostashar.model.device.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeviceForm {

    private String uid;
    private String imei;
    private String fireBaseRegId;
    private String ipAddress;
    private String model;

    @NotBlank
    private String useruid;

    public DeviceForm() {
    }
}