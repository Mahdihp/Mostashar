package ir.mostashar.model.assignDiscount.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AssignDiscountForm {

    private String assignDiscountId;

    private Boolean active;

    @NotBlank
    private String code;

    @NotBlank
    private Long expiryDate;

    @NotBlank
    private String userId;

    @NotBlank
    private String discountPackId;

    public AssignDiscountForm() {
    }
}
