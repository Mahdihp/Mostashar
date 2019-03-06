package ir.mostashar.model.feedback.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeedBackDTO extends BaseDTO {

    private String feedBackId;
    private Long creationDate;
    private String description;
    private Boolean read;
    private int score;
    private String clientId;
    private String requestId;

    public FeedBackDTO() {
    }

    public FeedBackDTO(Integer status, String message) {
        super(status, message);
    }
}
