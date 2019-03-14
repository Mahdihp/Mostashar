package ir.mostashar.model.acceptRequest.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "for Create Optianl: acceptRequestId")
public class AcceptRequestForm {

    private String acceptRequestId;

    @NotNull
    private String lawyerId;

    @NotNull
    private String requestId;

    public AcceptRequestForm() {
    }
}
