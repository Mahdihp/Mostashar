package ir.mostashar.model.wallet.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WalletDTO extends BaseDTO {

    private String walletId;

    private Integer value;

    private String bankAccountName;

    private String bankAccountNumber;

    private String bankAccountSheba;

    private String userId;

    private String organizationId;

    public WalletDTO() {
    }

    public WalletDTO(Integer status, String message) {
        super(status, message);
    }
}
