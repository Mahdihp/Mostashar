package ir.mostashar.model.acceptRequest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListAcceptRequestDTO extends BaseDTO {

    private List<AcceptRequestDTO> data;

    public ListAcceptRequestDTO() {
    }

    public ListAcceptRequestDTO(Integer status, String message) {
        super(status, message);
    }
}
