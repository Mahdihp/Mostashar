package ir.mostashar.model.feedback.dto;

import lombok.Data;

@Data
public class FeedBackForm {

    private String feedBackId;
    private Long creationDate;
    private String description;
    private boolean read = false;
    private int score;

    private String lawyerId;

    private String requestId;
}
