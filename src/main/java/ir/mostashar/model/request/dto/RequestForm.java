package ir.mostashar.model.request.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RequestForm {

    private String requestId;

    @NotBlank
    private String userId;

    @NotBlank
    private String fileId;

    @NotBlank
    private String adviceTypeId;

    public RequestForm() {
    }

}
