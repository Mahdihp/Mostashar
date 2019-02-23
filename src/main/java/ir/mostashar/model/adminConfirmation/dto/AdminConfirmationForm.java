package ir.mostashar.model.adminConfirmation.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AdminConfirmationForm {

    private String uid;

    @NotBlank
    private String title;
    private String description;
    private String targetUid;
    private short targetType;
    private boolean verified;
    private boolean deleted;
    private Long creationDate;

    public AdminConfirmationForm() {
    }
}
