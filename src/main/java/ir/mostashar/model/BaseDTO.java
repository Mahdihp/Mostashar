package ir.mostashar.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDTO {

    private Integer status;
    private String message;
    private String userId;
    private String walletId;
    private Boolean isActive;


    public BaseDTO() {
    }

    public BaseDTO(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public BaseDTO(Integer status, String message, Boolean isActive) {
        this.status = status;
        this.message = message;
        this.isActive = isActive;
    }
    public BaseDTO(Integer status, String message, String userId, Boolean isActive) {
        this.status = status;
        this.message = message;
        this.userId = userId;
        this.isActive = isActive;
    }
    public BaseDTO(Integer status, String message, String userId, String walletId, Boolean isActive) {
        this.status = status;
        this.message = message;
        this.userId = userId;
        this.walletId = walletId;
        this.isActive = isActive;
    }
}
