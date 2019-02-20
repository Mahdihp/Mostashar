package ir.mostashar.model.pack.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PackForm {


    private String uid;

    @NotBlank
    private String name;

    @NotBlank
    private String packUid;

    private String description;

    private int  minute;

    private int  lawyerpriceperminute;

    private int  totalprice;

    private boolean isActive = false;

    @NotBlank
    private String advicetypeUid;

    @NotBlank
    private String lawyerUid;

    public PackForm() {
    }
}
