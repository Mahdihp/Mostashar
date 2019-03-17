package ir.mostashar.model.billwallettransaction.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/17/19
 * Time: 10:36
 * https://github.com/mahdihp
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillWalletTransactionDTO extends BaseDTO {

    private String billWalletTransactionId;
    private String transactionNumber;
    private String trackingNumber;
    private Long transactionDate;
    private String bankAccountName;
    private String bankAccountNumber;
    private String bankAccountSheba;
    private String transactionType;
    private String description;
    private Long value;
    private String userId;
    private String requestId;

    public BillWalletTransactionDTO() {
    }

    public BillWalletTransactionDTO(Integer status, String message) {
        super(status, message);
    }
}
