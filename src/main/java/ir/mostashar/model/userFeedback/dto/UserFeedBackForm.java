package ir.mostashar.model.userFeedback.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserFeedBackForm {

    private String userFeedBackId;

    @NotNull
    private String description;

    public UserFeedBackForm() {
    }
}
