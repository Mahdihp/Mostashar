package ir.mostashar.model.factor.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "for Create Optianl: factorId")
public class FactorForm {

    private String factorId;

    private String serviceDescription;

    private String clientName;

    private String address;

    private Long tel;

//    private String postalCode;

    @NotNull
    private Long value;

    @NotNull
    private String billId;

}
