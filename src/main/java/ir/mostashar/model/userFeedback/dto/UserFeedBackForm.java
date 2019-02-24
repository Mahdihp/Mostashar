package ir.mostashar.model.userFeedback.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserFeedBackForm {

    private String uid;
    private int type;

    @NotBlank
    private String title;
    private String description;
    private Long creationDate;
    private boolean read;

    public UserFeedBackForm() {
    }
}
