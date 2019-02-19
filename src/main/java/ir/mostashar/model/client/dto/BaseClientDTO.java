package ir.mostashar.model.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseClientDTO extends BaseDTO {

    private String uid;

    private String firstName;

    private String lastName;

    private String fatherName;

    private String username;

    private String password;

    private String nationalId;

    private Long birthDate;

    private Boolean isOnline = false;

    private Integer score;

    private String avatarHashcode;

    private Boolean isActive = false;

    private Long mobileNumber;

    private String verificationCode;

    private Long creationDate;

    private Long modificationDate;

    private String roleName;

    private String jobTitle;

    private String address;

    private String postalCode;

    private String fieldOfStudy;

    private Long tel;

    public BaseClientDTO() {
    }

    public BaseClientDTO(Integer status, String message) {
        super(status, message);
    }
}
