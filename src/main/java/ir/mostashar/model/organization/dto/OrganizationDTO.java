package ir.mostashar.model.organization.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationDTO extends BaseDTO {

    private String orgUid;

    private String name;

    private String description;

    private String address;

    private String tel;

    private Long terminalId;

    private String username;

    private String password;

    private Long creationDate;

    private Long expiryDate;

    private Long orgStock;

    private Long appStock;

    public OrganizationDTO() {
    }

    public OrganizationDTO(Integer status, String message) {
        super(status, message);
    }
}
