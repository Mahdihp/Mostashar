package ir.mostashar.model.client.dto;

import ir.mostashar.model.BaseDTO;
import ir.mostashar.security.jwt.JwtResponse;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ClientDTO extends BaseDTO {

    private JwtResponse jwtResponse;

    public ClientDTO(String status, String message, String userid, boolean isActive, JwtResponse jwtResponse) {
        super(status, message, userid, isActive);
        this.jwtResponse = jwtResponse;
    }

    public ClientDTO(JwtResponse jwtResponse) {
        this.jwtResponse = jwtResponse;
    }
}
