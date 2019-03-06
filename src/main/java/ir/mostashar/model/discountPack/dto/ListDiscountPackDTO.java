package ir.mostashar.model.discountPack.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import ir.mostashar.model.discountPack.DiscountPack;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListDiscountPackDTO extends BaseDTO {

    private List<DiscountPackDTO> data;

    public ListDiscountPackDTO() {
    }
}
