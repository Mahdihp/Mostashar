package ir.mostashar.model.wallet.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WalletForm {

    @NotNull
    private String walletId;

    @NotNull
    private String bankAccountName;

    @NotNull
    private String bankAccountNumber;

    @NotNull
    private String bankAccountSheba;

    private String organizationId;
    private String orgUsername;

    private String userId;

    public WalletForm() {
    }
}
