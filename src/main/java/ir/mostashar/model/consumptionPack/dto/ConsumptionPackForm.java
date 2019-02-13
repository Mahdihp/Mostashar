package ir.mostashar.model.consumptionPack.dto;

import lombok.Data;

@Data
public class ConsumptionPackForm {

    private String packId;
    private String requestId;
    private Long consumptionTime;
    private Long value;
    private Integer type;
    private Long firstInstallmentDate;
    private Long lastInstallmentDate;
}
