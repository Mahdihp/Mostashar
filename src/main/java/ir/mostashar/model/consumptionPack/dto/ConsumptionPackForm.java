package ir.mostashar.model.consumptionPack.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ConsumptionPackForm {

    @NotBlank
    private String packId;

    @NotBlank
    private String requestId;

    @NotBlank
    private Long consumptionTime;

    @NotBlank
    private Long baseTime;

    @NotBlank
    private Short type;

    @NotBlank
    private Long firstInstallmentDate;

    @NotBlank
    private Long lastInstallmentDate;

    public ConsumptionPackForm() {
    }
}
