package ir.mostashar.model.bill.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillDTO extends BaseDTO {

    private String Id;
    private String transactionNumber;
    private String trackingNumber;
    private Long transactionDate;
    private String billStatus;
    private Long value;
    private String purchaseType;
    private String orgId;
    private String walletId;
    private String factorId;

    public BillDTO() {
    }

    public BillDTO(Integer status, String message) {
        super(status, message);
    }
}
