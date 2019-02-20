package ir.mostashar.model.failRequest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FailRequestDTO extends BaseDTO {

    private String uid;
    private Long creationDate;
    private String description;
    private String lawyerUid;
    private String requestUid;

    public FailRequestDTO() {
    }

    public FailRequestDTO(Integer status, String message) {
        super(status, message);
    }
}
