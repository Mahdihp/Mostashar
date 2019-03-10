package ir.mostashar.model.lawyer.dto;

import lombok.Data;

@Data
public class LawyerProfileForm {

    private String LawyerId;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String username;
    private String password;
    private String nationalId;
    private Boolean verified;
    private Long birthDate;
    private int score;
    private String avatarHashcode;
    private Boolean active;
    private Long mobileNumber;
    private String advicetypeId;


    public LawyerProfileForm() {
    }
}