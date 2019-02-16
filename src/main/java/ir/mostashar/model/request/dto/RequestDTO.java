package ir.mostashar.model.request.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestDTO extends BaseDTO {

    private String requestId;
    private String requestNumber;
    private String requestStatus;

    private String clientId;
    private String fileId;
    private String adviceTypeId;

    public RequestDTO() {
    }

    public RequestDTO(int status, String message) {
        super(status, message);
    }

    public RequestDTO(int status, String message, String requestId) {
        super(status, message);
        this.requestId = requestId;
    }
}
