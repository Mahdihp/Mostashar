package ir.mostashar.model.assignDiscount.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListAssignDiscountDTO extends BaseDTO {

    private List<AssignDiscountDTO> data;

    public ListAssignDiscountDTO() {
    }

    public ListAssignDiscountDTO(Integer status, String message) {
        super(status, message);
    }
}
