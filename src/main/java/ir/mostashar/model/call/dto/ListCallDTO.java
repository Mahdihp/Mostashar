package ir.mostashar.model.call.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListCallDTO extends BaseDTO {

    private List<CallDTO> data;

    public ListCallDTO() {
    }

    public ListCallDTO(Integer status, String message, List<CallDTO> data) {
        super(status, message);
        this.data = data;
    }
}
