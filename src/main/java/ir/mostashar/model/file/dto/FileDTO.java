package ir.mostashar.model.file.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileDTO extends BaseDTO {

    private String fileId;
    private String title;
    private String description;
    private Long creationDate;
    private Long modificationDate;
    private String clientid;

    public FileDTO() {
    }

    public FileDTO(String status, String message) {
        super(status, message);
    }

    public FileDTO(String status, String message, String fileId) {
        super(status, message);
        this.fileId = fileId;
    }
}
