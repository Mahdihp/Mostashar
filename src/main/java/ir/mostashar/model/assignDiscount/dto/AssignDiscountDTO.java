package ir.mostashar.model.assignDiscount.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssignDiscountDTO extends BaseDTO {

    private String assignDiscountId;
    private String userId;
    private String discountpackId;

    public AssignDiscountDTO() {
    }

    public AssignDiscountDTO(Integer status, String message) {
        super(status, message);
    }
}
