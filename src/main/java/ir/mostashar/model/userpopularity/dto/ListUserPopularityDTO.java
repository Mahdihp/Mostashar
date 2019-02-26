package ir.mostashar.model.userpopularity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListUserPopularityDTO extends BaseDTO {

    private List<UserPopularityDTO> data;

    public ListUserPopularityDTO() {
    }

}
