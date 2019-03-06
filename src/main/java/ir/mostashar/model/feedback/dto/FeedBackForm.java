package ir.mostashar.model.feedback.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FeedBackForm {

    private String feedBackId;
    private Long creationDate;

    @NotBlank
    private String description;

    private int score;

    @NotBlank
    private String lawyerId;

    @NotBlank
    private String requestId;

    public FeedBackForm() {
    }
}
