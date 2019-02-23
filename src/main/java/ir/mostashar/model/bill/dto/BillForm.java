package ir.mostashar.model.bill.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BillForm {

    private String uid;

    private String transactionNumber;

    private String trackingNumber;

    private Long transactionDate;

    private String billStatus;

    @NotBlank
    private long value;

    private String orgUid;

    @NotBlank
    private String walletUid;

    @NotBlank
    private String factorUid;

    public BillForm() {
    }
}
