package ir.mostashar.model.packsnapshot.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PackSnapshotForm {


    private String uid;

    @NotNull
    private String packname;

    private String packdescription;

    @NotNull
    private Integer packminute;

    @NotNull
    private Integer lawyerpriceperminute;

    @NotNull
    private Integer totalprice;


    private Boolean active;

    @NotNull
    private String advicetypeUid;


    @NotNull
    private String lawyerUid;
}
