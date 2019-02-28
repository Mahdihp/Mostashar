package ir.mostashar.model.installment;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class InstallmentForm {

    private String uid;

    @NotBlank
    private Integer installmentNumber;

    @NotBlank
    private Integer installmentTotalNumber;
    
    private Long creationDate;

    @NotBlank
    private long value;

    @NotBlank
    private String consumptionPackUid;

    @NotBlank
    private String factoruid;

    public InstallmentForm() {
    }
}
