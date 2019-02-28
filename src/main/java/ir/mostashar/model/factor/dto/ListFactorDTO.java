package ir.mostashar.model.factor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListFactorDTO extends BaseDTO {

    private List<FactorDTO> data;

    public ListFactorDTO() {
    }

    public ListFactorDTO(Integer status, String message, List<FactorDTO> data) {
        super(status, message);
        this.data = data;
    }
}
