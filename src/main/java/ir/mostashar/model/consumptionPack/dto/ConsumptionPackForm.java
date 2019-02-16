package ir.mostashar.model.consumptionPack.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsumptionPackForm {

    private String packId;
    private String requestId;
    private Long consumptionTime;
    private Long value;
    private Integer type;
    private Long firstInstallmentDate;
    private Long lastInstallmentDate;
}
