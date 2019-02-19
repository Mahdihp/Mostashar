package ir.mostashar.model.failRequest.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FailRequestForm  {

    private String uid;

    private Long creationDate;

    private String description;

    @NotBlank
    private String lawyerUid;

    @NotBlank
    private String requestUid;

    public FailRequestForm() {
    }
}
