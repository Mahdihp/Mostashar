package ir.mostashar.model.adviceType.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/19/19
 * Time: 11:34
 * https://github.com/mahdihp
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdviceTypeDTO extends BaseDTO {
    private String adviceTypeId;
    //    private Long parent;
    private String name;
    private String description;
//    private int type;

//    private AdviceType parent;


    public AdviceTypeDTO() {
    }
}
