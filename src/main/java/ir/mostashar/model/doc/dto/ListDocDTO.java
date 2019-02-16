package ir.mostashar.model.doc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListDocDTO extends BaseDTO {

    private List<DocDTO> docs;

    public ListDocDTO() {
    }
}
