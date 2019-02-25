package ir.mostashar.model.pack.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BuyPackDTO extends BaseDTO {

    private String factorUid;
    private String consumptionPackUid;



    public BuyPackDTO() {
    }

    public BuyPackDTO(int status, String message) {
        super(status, message);
    }

    public BuyPackDTO(Integer status, String message, String factorUid, String consumptionPackUid) {
        super(status, message);
        this.factorUid = factorUid;
        this.consumptionPackUid = consumptionPackUid;
    }

    public BuyPackDTO(Integer status, String message, String factorUid) {
        super(status, message);
        this.factorUid = factorUid;
    }
}
