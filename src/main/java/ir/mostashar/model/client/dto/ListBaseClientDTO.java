package ir.mostashar.model.client.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListBaseClientDTO extends BaseDTO {

    private List<BaseClientDTO> data;

    public ListBaseClientDTO() {
    }

    public ListBaseClientDTO(Integer status, String message) {
        super(status, message);
    }
}
