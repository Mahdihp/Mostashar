package ir.mostashar.model.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class ValidateCode {

    @NotBlank
    private String code;

    @NotBlank
    private String userid;

    public ValidateCode() {
    }

    public ValidateCode(@NotBlank String code, @NotBlank String userid) {
        this.code = code;
        this.userid = userid;
    }
}
