package ir.mostashar.model.installment.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class InstallmentForm {

    private String uid;

    @NotNull
    private Integer installmentNumber;

    @NotNull
    private Integer installmentTotalNumber;

    private Long creationDate;

    @NotNull
    private Long value;

    @NotNull
    private String consumptionPackUid;

    @NotNull
    private String factoruid;

    public InstallmentForm() {
    }
}
