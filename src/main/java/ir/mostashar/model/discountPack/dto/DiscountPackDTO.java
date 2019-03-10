package ir.mostashar.model.discountPack.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiscountPackDTO extends BaseDTO {

    private String discountPackId;
    private String title;
    private String codeOff;
    private Boolean active ;
    private Long creationDate;
    private Long expiryDate;
    private Integer value;
    private String type;

    public DiscountPackDTO() {
    }

    public DiscountPackDTO(Integer status, String message) {
        super(status, message);
    }
}
