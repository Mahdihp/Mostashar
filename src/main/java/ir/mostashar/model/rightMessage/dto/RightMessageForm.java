package ir.mostashar.model.rightMessage.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RightMessageForm {

    private String uid;

    @NotBlank
    private String title;
    private String description;
    private Long creationDate;
    private Long expiryDate;
    private boolean isActive;

    public RightMessageForm() {
    }
}
