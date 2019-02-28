package ir.mostashar.model.client.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FileForm {

    @NotBlank
    private String userId;

    @NotBlank
    private String title;

    private String description;

    public FileForm() {
    }

}
