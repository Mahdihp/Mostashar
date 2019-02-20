package ir.mostashar.model.packsnapshot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListPackSnapshotDTO extends BaseDTO {


    private List<PackSnapshotDTO> packs;

    public ListPackSnapshotDTO() {
    }

    public ListPackSnapshotDTO(Integer status, String message) {
        super(status, message);
    }
}
