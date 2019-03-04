package ir.mostashar.model.bill.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BillForm {

    private String Id;

    private String transactionNumber;

    private String trackingNumber;

    private Long transactionDate;

    private String billStatus; // Payment Status

    @NotBlank
    private int value; // پرسیده شود که ایا int or long

    private String orgId;

    @NotBlank
    private String userId;

    @NotBlank
    private String walletId;

    public BillForm() {
    }
}
