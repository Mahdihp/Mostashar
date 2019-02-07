package ir.mostashar.model.client.dto;

import ir.mostashar.model.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@AllArgsConstructor
@Data
public class FileUpdateForm extends BaseDTO {


    @NotBlank
    private UUID uid;

    @NotBlank
    private String fileNumber;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private Long modificationDate;

    public FileUpdateForm() {
    }
}
