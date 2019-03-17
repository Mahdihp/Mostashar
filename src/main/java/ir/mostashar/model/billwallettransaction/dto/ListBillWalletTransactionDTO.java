package ir.mostashar.model.billwallettransaction.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/17/19
 * Time: 10:42
 * https://github.com/mahdihp
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListBillWalletTransactionDTO extends BaseDTO {

    private List<BillWalletTransactionDTO> data;

    public ListBillWalletTransactionDTO() {
    }

    public ListBillWalletTransactionDTO(Integer status, String message) {
        super(status, message);
    }
}
