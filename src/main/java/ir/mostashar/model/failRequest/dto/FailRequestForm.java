package ir.mostashar.model.failRequest.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FailRequestForm  {

    private String Id;

    private String description;

    @NotBlank
    private String lawyerId;

    @NotBlank
    private String requestId;

    public FailRequestForm() {
    }
}
