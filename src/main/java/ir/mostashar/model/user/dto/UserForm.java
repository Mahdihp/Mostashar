package ir.mostashar.model.user.dto;

import lombok.Data;

@Data
public class UserForm {

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

    public UserForm() {
    }
}
