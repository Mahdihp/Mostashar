package ir.mostashar.model.rightMessage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;

/**
 * Created by mnm
 * User: mnm
 * Date: 3/13/19
 * Time: 21:30
 * http://github.com/ghaseminya
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListRightMessageDTO extends BaseDTO {
    List<RightMessageDTO> data;

    public ListRightMessageDTO() {
    }
}
