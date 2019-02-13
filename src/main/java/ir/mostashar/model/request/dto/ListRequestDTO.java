package ir.mostashar.model.request.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListRequestDTO extends BaseDTO {

    private List<RequestDTO> requests;

    public ListRequestDTO() {
        super();
    }

}
