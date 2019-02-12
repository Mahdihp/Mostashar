package ir.mostashar.model.file.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.file.File;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileDTO {

    private String status;
    private String message;
    private BaseFileDTO file;
    private String fileId;
    private List<BaseFileDTO> files;

    public FileDTO() {
    }

    public FileDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public FileDTO(String status, String message, String fileId) {
        this.status = status;
        this.message = message;
        this.fileId = fileId;
    }

    public static List<BaseFileDTO> convertListFileToListFileDTO(List<File> listFile){
        List<BaseFileDTO> baseFileDTOList = new ArrayList<>();
        for (File file : listFile){
            baseFileDTOList.add(new BaseFileDTO(file.getUid().toString(),file.getTitle(),file.getDescription(),file.getCreationDate(),file.getModificationDate(),file.getClient().getUid().toString()));
        }
        return baseFileDTOList;
    }
}
