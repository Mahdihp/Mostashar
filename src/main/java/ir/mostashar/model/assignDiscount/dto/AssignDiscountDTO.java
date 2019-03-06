package ir.mostashar.model.assignDiscount.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssignDiscountDTO extends BaseDTO {

    private String AssignDiscountId;
    private Boolean active ;
    private Long creationDate;
    private String userId;
    private String code;
    private String discountpackId;
    private Long expiryDate;

    public AssignDiscountDTO() {
    }

    public AssignDiscountDTO(Integer status, String message) {
        super(status, message);
    }
}
