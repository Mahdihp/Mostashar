package ir.mostashar.model.feedback.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListFeedBackDTO extends BaseDTO {

    private List<FeedBackDTO> data;

    public ListFeedBackDTO() {
    }

}
