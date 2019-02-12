package ir.mostashar.model.pack.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import ir.mostashar.model.adviceType.AdviceType;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PackDTO extends BaseDTO {

    private PackForm packForm;

    private String packId;
    private String name;
    private String description;
    private Integer  minute;
    private Boolean isActive ;
    private AdviceType advicetype;

    private List<PackDTO> packs;

    public PackDTO() {
    }

    public PackDTO(String status, String message) {
        super(status, message);
    }
}
