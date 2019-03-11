package ir.mostashar.model.lawyer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import ir.mostashar.model.expertise.dto.ExpertiseDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LawyerDTO extends BaseDTO {

    private String lawyerId;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String username;
    private String password;
    private String nationalId;
    private Long birthDate;
    private Boolean online;
    private Integer score;
    private String avatarHashcode;
    private Boolean active;
    private Long mobileNumber;
    private String verificationCode;
    private Long creationDate;
    private Long modificationDate;
    private Boolean available;
    private Integer level;
    private Integer pricePerMinute;
    private Boolean verified;
    private List<ExpertiseDTO> expertiseList;
    private String organizationId;
    private String advicetypeId;

    public LawyerDTO() {
    }

    public LawyerDTO(Integer status, String message) {
        super(status, message);
    }

    public LawyerDTO(Integer status, String message, String lawyerId) {
        super(status, message);
        this.lawyerId = lawyerId;
    }

    public LawyerDTO(Integer status, String message, String lawyerId, Boolean active) {
        super(status, message, active);
        this.lawyerId = lawyerId;
    }
}
