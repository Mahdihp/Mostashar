package ir.mostashar.model.failRequest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListFailRequestDTO extends BaseDTO {

    private List<FailRequestDTO> data;

    public ListFailRequestDTO() {
    }

    public ListFailRequestDTO(Integer status, String message) {
        super(status, message);
    }
}
