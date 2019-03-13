package ir.mostashar.model.feedback.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FeedBackForm {

    private String feedBackId;
    private Long creationDate;

    @NotNull
    private String description;

    private Integer score;

    @NotNull
    private String lawyerId;

    @NotNull
    private String requestId;

    public FeedBackForm() {
    }
}
