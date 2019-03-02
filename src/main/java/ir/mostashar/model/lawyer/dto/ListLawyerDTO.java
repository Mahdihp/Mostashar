package ir.mostashar.model.lawyer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListLawyerDTO extends BaseDTO {

    private List<LawyerDTO> data;

    public ListLawyerDTO() {
    }
}
