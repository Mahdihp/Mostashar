package ir.mostashar.model.client.dto;

import lombok.Data;

@Data
public class ClientProfileForm {

    private String userId;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String nationalId;
    private Boolean active ;
    private Long birthDate;
    private String avatarHashcode;
    private Long mobileNumber;
    private String jobTitle;
    private String address;
    private String postalCode;
    private String fieldOfStudy; //رشته تحصیلی
    private Long tel;

    public ClientProfileForm() {
    }
}
