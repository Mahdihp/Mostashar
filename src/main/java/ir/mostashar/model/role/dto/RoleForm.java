package ir.mostashar.model.role.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleForm {

    private String uid;
    private String description;

    @NotBlank
    private String roleName;
    private boolean userDefined;

    public RoleForm() {
    }
}
