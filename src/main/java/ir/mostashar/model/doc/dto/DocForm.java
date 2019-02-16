package ir.mostashar.model.doc.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DocForm {

    private String uid;

    private String checksum;

    private String hashCode;

    @NotBlank
    private String fileId;

    @NotBlank
    private String docType;

}
