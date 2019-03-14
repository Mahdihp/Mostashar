package ir.mostashar.model.rightMessage.dto;

import ir.mostashar.model.BaseDTO;

import javax.validation.constraints.NotBlank;

/**
 * Created by mnm
 * User: mnm
 * Date: 3/13/19
 * Time: 21:29
 * http://github.com/ghaseminya
 */
public class RightMessageDTO extends BaseDTO {

    private String uid;

    @NotBlank
    private String title;
    private String description;
    private Long creationDate;
    private Long expiryDate;
    private boolean active;
}
