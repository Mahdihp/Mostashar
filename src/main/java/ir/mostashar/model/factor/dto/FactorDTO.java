package ir.mostashar.model.factor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FactorDTO extends BaseDTO {

    private String uid;
    private String serviceDescription;
    private String clientName;
    private String clientCode;
    private String address;
    private Long tel;
    private String postalCode;
    private String factorNumber;
    private Long creationDate;
    private Long value;
    private Boolean deleted = false;
    private String billUid;
    private String installmentUid;

    public FactorDTO() {
    }

    public FactorDTO(Integer status, String message) {
        super(status, message);
    }
}
