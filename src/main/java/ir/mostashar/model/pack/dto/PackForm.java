package ir.mostashar.model.pack.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PackForm {


    @NotBlank
    private String uid;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private Long  value;

    @NotBlank
    private Boolean isActive;

    @NotBlank
    private String adviceId;

    public PackForm() {
    }
}
