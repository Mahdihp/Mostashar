package ir.mostashar.model.request.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestDTO extends BaseDTO {

    private String requestId;
    private String requestNumber;
    private String description;

    private String clientId;
    private String fileId;
    private String adviceTypeId;

    public RequestDTO() {
    }

    public RequestDTO(String status, String message) {
        super(status, message);
    }

    public RequestDTO(String status, String message, String requestId) {
        super(status, message);
        this.requestId = requestId;
    }
}
