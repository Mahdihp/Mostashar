package ir.mostashar.model.file.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseFileDTO {

    private String fileId;
    private String title;
    private String description;
    private Long creationDate;
    private Long modificationDate;
    private String clientid;

    public BaseFileDTO() {
    }
}
