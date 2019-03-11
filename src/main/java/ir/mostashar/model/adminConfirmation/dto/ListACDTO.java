package ir.mostashar.model.adminConfirmation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListACDTO extends BaseDTO {
    private List<ACDTO> data;

    public ListACDTO() {
    }
}
