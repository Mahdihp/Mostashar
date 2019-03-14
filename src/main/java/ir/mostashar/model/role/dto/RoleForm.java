package ir.mostashar.model.role.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RoleForm {

    private String uid;
    private String description;

    @NotNull
    private String roleName;
    private Boolean userDefined;

    public RoleForm() {
    }
}
