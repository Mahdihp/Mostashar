package ir.mostashar.model.rightMessage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by mnm
 * User: mnm
 * Date: 3/13/19
 * Time: 21:29
 * http://github.com/ghaseminya
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RightMessageDTO extends BaseDTO {

    private String rightMessageId;

    @NotBlank
    private String title;
    private String description;
    private Long creationDate;
    private Long expiryDate;
}
