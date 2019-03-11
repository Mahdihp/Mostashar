package ir.mostashar.model.userFeedback.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserFeedBackForm {

    private String userFeedBackId;

    @NotBlank
    private String description;

    public UserFeedBackForm() {
    }
}
