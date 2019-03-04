package ir.mostashar.model.call.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CallDTO extends BaseDTO {

    private String callId;

    private Integer failedRetriesCount;

    private String callStatus;

    private Integer callType;

    private Long startTime;

    private Long endTime;

    private Long creationDate;

    private String clientId;

    private String lawyerId;

    private String requestId;

    private String docId;

    public CallDTO() {
    }

    public CallDTO(Integer status, String message) {
        super(status, message);
    }
}
