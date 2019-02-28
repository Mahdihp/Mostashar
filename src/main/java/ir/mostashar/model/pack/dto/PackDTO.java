package ir.mostashar.model.pack.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PackDTO extends BaseDTO {

    private String uid;

    private String name;

    private String description;

    private Integer  minute;

    private Long priceTotal;

    private Boolean active;

    private String advicetypeUid;

    public PackDTO() {
    }

    public PackDTO(int status, String message) {
        super(status, message);
    }

}
