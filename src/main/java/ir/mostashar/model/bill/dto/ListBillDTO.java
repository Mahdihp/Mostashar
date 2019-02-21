package ir.mostashar.model.bill.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListBillDTO extends BaseDTO {

    private List<BillDTO> data;

    public ListBillDTO() {
    }
}
