package ir.mostashar.model.doc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocDTO extends BaseDTO {

    private String docId;
    private String checksum;
    private String hashCode;
    private String docType;
    private Byte[] data;
    private Long creationDate;
    private String fileId;

    public DocDTO() {
    }

    public DocDTO(Integer status, String message) {
        super(status, message);
    }
}
