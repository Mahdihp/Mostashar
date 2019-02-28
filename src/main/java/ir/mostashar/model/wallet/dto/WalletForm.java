package ir.mostashar.model.wallet.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class WalletForm {

    @NotBlank
    private String walletId;

    private String bankAccountName;

    private String bankAccountNumber;

    private String bankAccountSheba;

    private String organizationId;

    public WalletForm() {
    }
}
