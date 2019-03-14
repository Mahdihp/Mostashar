package ir.mostashar.model.organization.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationDTO extends BaseDTO {

    private String orgId;
    private String walletId;

    private String name;

    private String description;

    private String address;

    private String tel;

    private Long terminalId;

    private String username;

    private String password;

    private Long creationDate;

    private Long expiryDate;

    private Integer percentOrgStock;
    private Integer percentMasterStock;
    private Boolean verified;


    public OrganizationDTO() {
    }

    public OrganizationDTO(Integer status, String message) {
        super(status, message);
    }

    public OrganizationDTO(Integer status, String message, String orgId) {
        super(status, message);
        this.orgId = orgId;
    }



}
