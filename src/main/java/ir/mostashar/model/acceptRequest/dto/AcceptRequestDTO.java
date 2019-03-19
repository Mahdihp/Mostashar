package ir.mostashar.model.acceptRequest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AcceptRequestDTO extends BaseDTO {

    private String AcceptRequesId;

    private Long creationDate;

    private Long verified;

    private Long finishedTimeFile;

    private Boolean acceptedByClient;

    private Boolean reading;

    private String lawyerId;

    private String requestId;

    public AcceptRequestDTO() {
    }

    public AcceptRequestDTO(Integer status, String message) {
        super(status, message);
    }
}
