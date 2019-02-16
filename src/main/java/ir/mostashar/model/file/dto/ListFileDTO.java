package ir.mostashar.model.file.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListFileDTO extends BaseDTO {


    private List<FileDTO> files;

    public ListFileDTO() {
    }

    public ListFileDTO(int status, String message) {
        super(status,message);
    }


}
