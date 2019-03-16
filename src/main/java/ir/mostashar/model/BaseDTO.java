package ir.mostashar.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDTO {

    private Integer status;
    private String message;
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

}
