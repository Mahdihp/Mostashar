package ir.mostashar.model.client.dto;

import ir.mostashar.model.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class FileUpdateForm extends BaseDTO {


    @NotNull
    private String fileId;

    private String fileNumber;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private String modificationDate;

    public FileUpdateForm() {
    }


}
