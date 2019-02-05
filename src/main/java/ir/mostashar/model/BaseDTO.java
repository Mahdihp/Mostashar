package ir.mostashar.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseDTO {

    private String status;
    private String message;
    private String userid;
    private boolean isActive;

    public BaseDTO() {
    }

}
