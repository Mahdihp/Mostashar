package ir.mostashar.model.factordetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListFactorDetailDTO extends BaseDTO {

    private List<FactorDetailDTO> data;

    public ListFactorDetailDTO() {
    }

    public ListFactorDetailDTO(Integer status, String message) {
        super(status, message);
    }
}
