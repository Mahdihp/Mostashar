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
    private String status;
    private Long value;
    private String orgUid;
    private String walletUid;
    private String factorUid;

    public BillDTO() {
    }

    public BillDTO(String uid, String transactionNumber, String trackingNumber, Long transactionDate, String status, Long value, String orgUid, String walletUid, String factorUid) {
        this.uid = uid;
        this.transactionNumber = transactionNumber;
        this.trackingNumber = trackingNumber;
        this.transactionDate = transactionDate;
        this.status = status;
        this.value = value;
        this.orgUid = orgUid;
        this.walletUid = walletUid;
        this.factorUid = factorUid;
    }

}
