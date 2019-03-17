package ir.mostashar.model.billwallettransaction.dto;

import lombok.Data;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/17/19
 * Time: 10:07
 * https://github.com/mahdihp
 */
@Data
public class BillWalletTransactionForm {

    private String billWalletTransactionId;
    private String transactionNumber;
    private String trackingNumber;
    private Long transactionDate;
    private String bankAccountName;
    private String bankAccountNumber;
    private String bankAccountSheba;
    private String transactionType;
    private String description;
    private long value;
    private String userId;
    private String requestId;

    public BillWalletTransactionForm() {
    }
}
