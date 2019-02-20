package ir.mostashar.model.installment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstallmentDTO extends BaseDTO {

    private String uid;
    private Integer installmentNumber;
    private Integer installmentTotalNumber;
    private Long creationDate;
    private Long value;
    private String consumptionPackUid;

    public InstallmentDTO() {
    }

    public InstallmentDTO(Integer status, String message) {
        super(status, message);
    }
}
