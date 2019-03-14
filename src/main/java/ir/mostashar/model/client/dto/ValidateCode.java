package ir.mostashar.model.client.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class ValidateCode {

    @NotNull
    private String code;

    @NotNull
    private String userId;

    public ValidateCode() {
    }

    public ValidateCode(@NotNull String code, @NotNull String userId) {
        this.code = code;
        this.userId = userId;
    }
}
