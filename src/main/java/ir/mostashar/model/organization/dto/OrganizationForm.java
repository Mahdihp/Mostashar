package ir.mostashar.model.organization.dto;

import lombok.Data;

@Data
public class OrganizationForm {

    private String orgUid;

    private String name;

    private String description;

    private String address;

    private String tel;

    private long terminalId;

    private String username;

    private String password;

    private Long creationDate;

    private Long expiryDate;

    private Long orgStock;

    private Long appStock;

    private boolean isVerified;

    public OrganizationForm() {
    }
}
