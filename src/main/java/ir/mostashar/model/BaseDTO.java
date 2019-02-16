package ir.mostashar.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDTO {

    private Integer status;
    private String message;
    private String userid;
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

    public BaseDTO(Integer status, String message, String userid, Boolean isActive) {
        this.status = status;
        this.message = message;
        this.userid = userid;
        this.isActive = isActive;
    }
}
