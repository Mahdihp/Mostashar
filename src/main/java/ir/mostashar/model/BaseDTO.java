package ir.mostashar.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDTO {

    private Integer status;
    private String message;
    private String userId;
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

    public BaseDTO(Integer status, String message, String userId, Boolean active) {
        this.status = status;
        this.message = message;
        this.userId = userId;
        this.active = active;
    }

    public BaseDTO(Integer status, String message, String userId, String walletId, Boolean active) {
        this.status = status;
        this.message = message;
        this.userId = userId;
        this.walletId = walletId;
        this.active = active;
    }
}
