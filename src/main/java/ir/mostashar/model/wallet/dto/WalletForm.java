package ir.mostashar.model.wallet.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class WalletForm {

    @NotBlank
    private String walletId;

    @NotBlank
    private String bankAccountName;

    @NotBlank
    private String bankAccountNumber;

    @NotBlank
    private String bankAccountSheba;

    private String organizationId;
    private String orgUsername;

    private String userId;

    public WalletForm() {
    }
}
