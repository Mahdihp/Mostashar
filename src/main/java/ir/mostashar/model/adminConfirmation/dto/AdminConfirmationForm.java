package ir.mostashar.model.adminConfirmation.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AdminConfirmationForm {

    private String adminConfirmationId;

    @NotBlank
    private String title;
    private String description;
    private String targetUid;

    @NotBlank
    private String typeConfirmation;

    private String lawyerId;
    private String userId;


    public AdminConfirmationForm() {
    }
}
