package ir.mostashar.model;

import lombok.Data;

@Data
public class BaseDTO {

    private String status;
    private String message;

    public BaseDTO() {
    }

    public BaseDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
