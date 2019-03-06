package ir.mostashar.model.userpopularity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPopularityDTO extends BaseDTO {

    private String userId;
    private String userPopularId;
    private String adviceName;

    public UserPopularityDTO() {
    }
}
