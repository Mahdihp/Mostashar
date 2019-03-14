package ir.mostashar.model.rightMessage.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RightMessageForm {

    private String uid;

    @NotNull
    private String title;
    private String description;
    private Long creationDate;
    private Long expiryDate;

    public RightMessageForm() {
    }
}
