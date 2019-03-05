package ir.mostashar.model.activity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LawyerActivityForm {

    private String LawyerActivityId;

    private int type;

    @NotBlank
    private String title;

    private String description;

    private Long creationDate;

    @NotBlank
    private String lawyerId;

    private String docid;

    private String fileId;

    public LawyerActivityForm() {
    }
}
