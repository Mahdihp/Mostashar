package ir.mostashar.model.bill.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "for Create Optianl: billId")
public class BillForm {


    private String billId;
    private String transactionNumber;
    private String trackingNumber;
    private Long transactionDate;
    private String billStatus; // Payment Status

    @NotNull
    private Integer value; // پرسیده شود که ایا int or long

//    private String orgId;

    @NotNull
    private String userId;

    @NotNull
    private String walletId;

    @NotNull
    private String requestId;

    public BillForm() {
    }
}
