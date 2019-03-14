package ir.mostashar.model.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListUserDTO extends BaseDTO {

    private List<UserDTO> data;

    public ListUserDTO() {
    }

    public ListUserDTO(Integer status, String message) {
        super(status, message);
    }
}
