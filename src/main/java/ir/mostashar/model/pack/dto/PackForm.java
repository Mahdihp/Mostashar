package ir.mostashar.model.pack.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "for Create Optianl: packId")
public class PackForm {

    private String packId;
    private String name;
    private String description;
    private int minute;
    private Boolean active;
    private String advicetypeId;
}
