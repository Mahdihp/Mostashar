package ir.mostashar.model.pack.dto;

import ir.mostashar.model.BaseDTO;
import lombok.Data;

@Data
public class PackDTO extends BaseDTO {

    PackForm packForm;

    public PackDTO() {
    }

    public PackDTO(String status, String message) {
        super(status, message);
    }
}
