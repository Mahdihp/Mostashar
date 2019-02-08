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
    private BaseFileDTO baseFileDTO;

    private List<BaseFileDTO> baseFileDTOList;

    public FileDTO() {
    }

    public FileDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public static List<BaseFileDTO> convertListFileToListFileDTO(List<File> listFile){
        List<BaseFileDTO> baseFileDTOList = new ArrayList<>();
        for (File file : listFile){
            baseFileDTOList.add(new BaseFileDTO(file.getUid().toString(),file.getTitle(),file.getDescription(),file.getCreationDate(),file.getModificationDate(),file.getClient().getUid().toString()));
        }
        return baseFileDTOList;
    }
}