package ir.mostashar.model.wallet.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListWalletDTO extends BaseDTO {

    private List<WalletDTO> data;

    public ListWalletDTO() {
    }

    public ListWalletDTO(Integer status, String message, List<WalletDTO> data) {
        super(status, message);
        this.data = data;
    }
}
