package ir.mostashar.model.factor.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FactorForm {

    private String factorId;

    private String serviceDescription;

    private String clientName;

    private String address;

    private Long tel;

    private String postalCode;

    @NotNull
    private Long value;

    @NotNull
    private String billId;

}
