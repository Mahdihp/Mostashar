package ir.mostashar.model.adviceType.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/19/19
 * Time: 11:35
 * https://github.com/mahdihp
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListAdviceTypeDTO extends BaseDTO {

    private List<AdviceTypeDTO> data;

    public ListAdviceTypeDTO() {
    }
}
