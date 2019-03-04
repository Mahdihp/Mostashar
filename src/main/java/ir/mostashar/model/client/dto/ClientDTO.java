package ir.mostashar.model.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDTO extends BaseDTO {

    private String userId;

    private String firstName;

    private String lastName;

    private String fatherName;

    private String username;

    private String password;

    private String nationalId;

    private Long birthDate;

    private Boolean isOnline ;

    private Integer score;

    private String avatarHashcode;

    private Boolean active ;

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

    private String walletId;

    public ClientDTO() {
    }

    public ClientDTO(Integer status, String message) {
        super(status, message);
    }
}
