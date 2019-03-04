package ir.mostashar.model.lawyer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import ir.mostashar.model.expertise.dto.ExpertiseDTO;
import ir.mostashar.model.role.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LawyerDTO extends BaseDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String username;
    private String password;
    private String nationalId;
    private Long birthDate;
    private Boolean online ;
    private Integer score;
    private String avatarHashcode;
    private Boolean active ;
    private Long mobileNumber;
    private String verificationCode;
    private Long creationDate;
    private Long modificationDate;
    private String roleName;
    private Boolean available;
    private Integer level ;
    private Integer pricePerMinute ;
    private Boolean verified ;
    private List<ExpertiseDTO> expertiseList;
    private String organizationId;
    private String advicetypeId;

    public LawyerDTO() {
    }

    public LawyerDTO(Integer status, String message) {
        super(status, message);
    }
}
