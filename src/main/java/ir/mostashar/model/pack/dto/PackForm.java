package ir.mostashar.model.pack.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PackForm {


    @NotBlank
    private String uid;

    private String name;
    private String description;

    @NotBlank
    private Integer  pricePerMinute;

    @NotBlank
    private Boolean isActive;

    public PackForm() {
    }
}
