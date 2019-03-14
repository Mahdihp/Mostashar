package ir.mostashar.model.discountPack.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DiscountPackForm {

    private String discountPackId;

    @NotNull
    private String title;

    @NotNull
    private String codeOff;

    @NotNull
    private Boolean active ;

    @NotNull
    private Long expiryDate;

    @NotNull
    private Integer value;

    @NotNull
    private Integer type;

    public DiscountPackForm() {
    }
}
