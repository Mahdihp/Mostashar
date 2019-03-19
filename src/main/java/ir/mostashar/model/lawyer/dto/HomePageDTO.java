package ir.mostashar.model.lawyer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/19/19
 * Time: 11:14
 * https://github.com/mahdihp
 */


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HomePageDTO extends BaseDTO {

    private String money;
    private Boolean avalable;

    public HomePageDTO() {
    }

    public HomePageDTO(Integer status, String message) {
        super(status, message);
    }
}
