package ir.mostashar.model.factordetails.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FactorDetailDTO extends BaseDTO {

    private String factorDetailId;
    private String itemName;
    private Integer countItem;
    private Long pricePer;
    private Long totalPrice;
    private String description;
    private String factorId;

    public FactorDetailDTO() {
    }

    public FactorDetailDTO(Integer status, String message) {
        super(status, message);
    }
}
