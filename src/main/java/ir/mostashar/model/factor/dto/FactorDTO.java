package ir.mostashar.model.factor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FactorDTO extends BaseDTO {

    private String factorId;
    private String serviceDescription;
    private String clientName;
    private String clientCode;
    private String address;
    private Long tel;
    //    private String postalCode;
    private Long factorNumber;
    private Long creationDate;
    private Long value;
    private Boolean deleted;
    private String billUid;
    private String installmentId;

    public FactorDTO() {
    }

    public FactorDTO(Integer status, String message) {
        super(status, message);
    }

    public FactorDTO(Integer status, String message, String factorId) {
        super(status, message);
        this.factorId = factorId;
    }
}
