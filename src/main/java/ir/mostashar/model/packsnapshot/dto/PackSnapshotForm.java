package ir.mostashar.model.packsnapshot.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PackSnapshotForm {


    private String uid;

    @NotBlank
    private String packname;

    private String packdescription;

    @NotBlank
    private int  packminute;

    @NotBlank
    private int  lawyerpriceperminute;

    @NotBlank
    private int  totalprice;


    private boolean isActive = false;

    @NotBlank
    private String advicetypeUid;


    @NotBlank
    private String lawyerUid;
}
