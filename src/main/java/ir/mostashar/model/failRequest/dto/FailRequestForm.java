package ir.mostashar.model.failRequest.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "for Create Optianl: failRequestId")
public class FailRequestForm  {

    private String failRequestId;
    private String description;

    @NotNull
    private String lawyerId;

    @NotNull
    private String requestId;

    public FailRequestForm() {
    }
}
