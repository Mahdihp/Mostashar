package ir.mostashar.model.organization.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListOrganizationDTO extends BaseDTO {

    private List<OrganizationDTO> data;

    public ListOrganizationDTO() {
    }

    public ListOrganizationDTO(Integer status, String message) {
        super(status, message);
    }

}
