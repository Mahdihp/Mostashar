package ir.mostashar.model.request.dto;

import ir.mostashar.model.request.RequestStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RequestForm {

    private String requestId;
    private String status;

    @NotBlank
    private String clientId;

    @NotBlank
    private String fileId;

    @NotBlank
    private String adviceTypeId;

    public RequestForm() {
    }

}
