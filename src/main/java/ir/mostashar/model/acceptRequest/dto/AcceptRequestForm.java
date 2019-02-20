package ir.mostashar.model.acceptRequest.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AcceptRequestForm {

    private String uid;

    private Long acceptDate;

    private Long verified;

    private Long finishedTimeFile;

    @NotBlank
    private String lawyerUid;

    @NotBlank
    private String requestUid;

    public AcceptRequestForm() {
    }
}
