package ir.mostashar.model.user.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO extends BaseDTO {

    private String userid;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String username;
    private String password;
    private String email;
    private String nationalId;
    private Long birthDate;
    private Long mobileNumber;

    public UserDTO() {
    }

    public UserDTO(Integer status, String message) {
        super(status, message);
    }
}
