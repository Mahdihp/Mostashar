package ir.mostashar.model.adminConfirmation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ACDTO extends BaseDTO {

    private String adminConfirmationId;
    private String title;
    private String description;
    private String targetUid;
    private String typeConfirmation;
    private Boolean verified;
    private Long creationDate;
    private Long verifiedDate;
    private String lawyerId;
    private String userId;

    public ACDTO() {
    }
}
