package ir.mostashar.model.acceptRequest.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AcceptRequestForm {

    private String uid;

    @NotBlank
    private String lawyerId;

    @NotBlank
    private String requestId;

    public AcceptRequestForm() {
    }
}
