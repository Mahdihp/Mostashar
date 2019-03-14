package ir.mostashar.model.organization.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "for Create Optianl: orgId")
public class OrganizationForm {

    private String orgId;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private String address;

    @NotNull
    private String tel;

    private Long terminalId;

    private String username;

    private String password;

    private Long expiryDate;

    private Integer percentOrgStock;
    private Integer percentMasterStock;

    public OrganizationForm() {
    }
}
