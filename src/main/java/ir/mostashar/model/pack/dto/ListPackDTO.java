package ir.mostashar.model.pack.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListPackDTO extends BaseDTO {


    private List<PackDTO> packs;

    public ListPackDTO() {
    }

    public ListPackDTO(String status, String message) {
        super(status, message);
    }

    public ListPackDTO(List<PackDTO> packs) {
        this.packs = packs;
    }

    public ListPackDTO(String status, String message, List<PackDTO> packs) {
        super(status, message);
        this.packs = packs;
    }
}
