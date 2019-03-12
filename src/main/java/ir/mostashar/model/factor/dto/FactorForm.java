package ir.mostashar.model.factor.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FactorForm {

    private String factorId;

    private String serviceDescription;

    private String clientName;

    private String address;

    private Long tel;

    private String postalCode;

    @NotBlank
    private long value;

    @NotBlank
    private String billId;

}
