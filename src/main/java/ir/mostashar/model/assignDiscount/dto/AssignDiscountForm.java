package ir.mostashar.model.assignDiscount.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AssignDiscountForm {


    @NotBlank
    private String userId;

    @NotBlank
    private String discountCode;

    public AssignDiscountForm() {
    }
}
