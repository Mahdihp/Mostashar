package ir.mostashar.model.request.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestForm {

    private String requestId;

    @NotNull
    private String userId;

    @NotNull
    private String fileId;

    @NotNull
    private String fileNumber;

    @NotNull
    private String adviceTypeId;

    public RequestForm() {
    }

}
