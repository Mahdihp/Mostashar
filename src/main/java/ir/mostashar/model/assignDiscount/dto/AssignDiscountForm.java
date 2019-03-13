package ir.mostashar.model.assignDiscount.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AssignDiscountForm {


    @NotNull
    private String userId;

    @NotNull
    private String discountCode;

    public AssignDiscountForm() {
    }
}
