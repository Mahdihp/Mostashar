package ir.mostashar.model.organization.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OrganizationForm {

    private String orgId;

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private String address;

    @NotBlank
    private String tel;

    private long terminalId;

    private String username;

    private String password;

    private Long expiryDate;

    private Long orgStock;

    private Long appStock;

    public OrganizationForm() {
    }
}
