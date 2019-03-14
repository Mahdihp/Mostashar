package ir.mostashar.model.consumptionPack.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ConsumptionPackForm {

    @NotNull
    private String packId;

    @NotNull
    private String requestId;

    @NotNull
    private Long consumptionTime;

    @NotNull
    private Long baseTime;

    @NotNull
    private Short type;

    @NotNull
    private Long firstInstallmentDate;

    @NotNull
    private Long lastInstallmentDate;

    public ConsumptionPackForm() {
    }
}
