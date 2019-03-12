package ir.mostashar.model.file.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileDTO extends BaseDTO {

    private String fileId;
    private Long fileNumber;
    private String title;
    private String description;
    private Long creationDate;
    //    private Long modificationDate;
    private String clientId;

    public FileDTO() {
    }

    public FileDTO(int status, String message) {
        super(status, message);
    }

    public FileDTO(int   status, String message, String fileId) {
        super(status, message);
        this.fileId = fileId;
    }
}
