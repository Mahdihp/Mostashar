package ir.mostashar.model.file.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileDTO {

    private String status;
    private String message;
    private String fileId;
}
