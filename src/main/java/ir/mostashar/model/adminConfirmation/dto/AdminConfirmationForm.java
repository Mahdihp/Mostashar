package ir.mostashar.model.adminConfirmation.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AdminConfirmationForm {

    private String adminConfirmationId;

    @NotNull
    private String title;
    private String description;
    private String targetUid;

    @NotNull
    private int typeConfirmation;

    private String lawyerId;
    private String userId;


    public AdminConfirmationForm() {
    }
}
