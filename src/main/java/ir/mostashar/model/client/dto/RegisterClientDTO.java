package ir.mostashar.model.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import ir.mostashar.security.jwt.JwtResponse;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterClientDTO extends BaseDTO {

    private JwtResponse jwtResponse;

    public RegisterClientDTO(int status, String message, String userid, boolean isActive, JwtResponse jwtResponse) {
        super(status, message, userid, isActive);
        this.jwtResponse = jwtResponse;
    }

    public RegisterClientDTO(JwtResponse jwtResponse) {
        this.jwtResponse = jwtResponse;
    }
}
