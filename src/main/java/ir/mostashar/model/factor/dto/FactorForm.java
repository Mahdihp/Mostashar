package ir.mostashar.model.factor.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FactorForm {

    private String factorId;

    private String serviceDescription;

    private String clientName;

    private String clientCode;

    private String address;

    private Long tel;

    private String postalCode;

    private String factorNumber;

    private Long creationDate;

    @NotBlank
    private long value;

    private boolean deleted = false;

    @NotBlank
    private String billId;

}
