package ir.mostashar.model.installment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListInstallmentDTO extends BaseDTO {
    private List<InstallmentDTO> data;

    public ListInstallmentDTO() {
    }

    public ListInstallmentDTO(Integer status, String message) {
        super(status, message);
    }
}
