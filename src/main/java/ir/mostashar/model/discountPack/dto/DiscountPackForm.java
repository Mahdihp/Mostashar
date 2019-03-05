package ir.mostashar.model.discountPack.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DiscountPackForm {

    private String discountPackId;

    @NotBlank
    private String name;

    @NotBlank
    private long value;
    private int type;

    public DiscountPackForm() {
    }
}
