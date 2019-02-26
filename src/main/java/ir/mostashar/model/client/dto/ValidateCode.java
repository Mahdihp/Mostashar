package ir.mostashar.model.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class ValidateCode {

    @NotBlank
    private String code;

    @NotBlank
    private String userId;

    public ValidateCode() {
    }

    public ValidateCode(@NotBlank String code, @NotBlank String userId) {
        this.code = code;
        this.userId = userId;
    }
}
