package ir.mostashar.model.doc.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DocForm {

    private String uid;

    private String checksum;

    private String hashCode;

    @NotNull
    private String fileId;

    @NotNull
    private Integer docType;

}
