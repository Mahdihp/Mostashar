package ir.mostashar.model.bill.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillDTO extends BaseDTO {

    private String uid;
    private String transactionNumber;
    private String trackingNumber;
    private Long transactionDate;
    private String billStatus;
    private Long value;
    private String orgUid;
    private String walletUid;
    private String factorUid;

    public BillDTO() {
    }


}
