package ir.mostashar.model.assignDiscount.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AssignDiscountForm {

    private String assignDiscountId;

    @NotBlank
    private String userId;

    @NotBlank
    private String discountPackId;

    public AssignDiscountForm() {
    }
}
