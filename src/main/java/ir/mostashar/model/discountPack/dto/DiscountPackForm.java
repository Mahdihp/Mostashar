package ir.mostashar.model.discountPack.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DiscountPackForm {

    private String discountPackId;

    @NotBlank
    private String title;

    @NotBlank
    private String codeOff;

    @NotBlank
    private Boolean active ;

    @NotBlank
    private Long expiryDate;

    @NotBlank
    private Integer value;

    @NotBlank
    private String type;

    public DiscountPackForm() {
    }
}
