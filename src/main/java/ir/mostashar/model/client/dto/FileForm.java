package ir.mostashar.model.client.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FileForm {

    @NotNull
    private String userId;

    @NotNull
    private String title;

    private String description;

    public FileForm() {
    }

}
