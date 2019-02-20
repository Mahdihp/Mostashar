package ir.mostashar.model.acceptRequest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AcceptRequestDTO extends BaseDTO {

    private String uid;

    private Long acceptDate;

    private Long verified;

    private Long finishedTimeFile;

    private String lawyerUid;

    private String requestUid;

    public AcceptRequestDTO() {
    }

    public AcceptRequestDTO(Integer status, String message) {
        super(status, message);
    }
}
