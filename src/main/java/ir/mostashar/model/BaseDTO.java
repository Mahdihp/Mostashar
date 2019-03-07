package ir.mostashar.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDTO {

    private Integer status;
    private String message;
    private String clientId;
    private String walletId;
    private Boolean active;


    public BaseDTO() {
    }

    public BaseDTO(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public BaseDTO(Integer status, String message, Boolean active) {
        this.status = status;
        this.message = message;
        this.active = active;
    }
    public BaseDTO(Integer status, String message, String clientId, Boolean active) {
        this.status = status;
        this.message = message;
        this.clientId = clientId;
        this.active = active;
    }
    public BaseDTO(Integer status, String message, String clientId, String walletId, Boolean active) {
        this.status = status;
        this.message = message;
        this.clientId = clientId;
        this.walletId = walletId;
        this.active = active;
    }
}
