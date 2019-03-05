package ir.mostashar.model.discountPack.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiscountPackDTO extends BaseDTO {

    private String discountPackId;
    private String name;
    private Long value;
    private Integer type;

    public DiscountPackDTO() {
    }
}
