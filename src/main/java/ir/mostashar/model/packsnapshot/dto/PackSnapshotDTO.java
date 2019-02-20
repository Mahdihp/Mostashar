package ir.mostashar.model.packsnapshot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PackSnapshotDTO extends BaseDTO {

    private String packId;
    private String packname;
    private String packdescription;
    private Integer  packminute;
    private Integer  lawyerpriceperminute;
    private Integer  totalprice;
    private Boolean isActive ;

    public PackSnapshotDTO() {
    }


}
