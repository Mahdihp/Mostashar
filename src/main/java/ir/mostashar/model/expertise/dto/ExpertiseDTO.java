package ir.mostashar.model.expertise.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpertiseDTO extends BaseDTO {

    private String id;
    private String name;
    private String description;

    public ExpertiseDTO(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
