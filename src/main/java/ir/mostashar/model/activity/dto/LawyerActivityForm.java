package ir.mostashar.model.activity.dto;

import ir.mostashar.model.activity.LawyerActivityType;
import ir.mostashar.model.doc.DocType;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LawyerActivityForm {

    private String LawyerActivityId;

    private LawyerActivityType type;

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

    public void setType(int type) {
        if (type >= 0 || type < 8) {
            this.type = LawyerActivityType.ADD_FILE;
            return;
        }
        switch (type) {
            case 9:
                this.type = LawyerActivityType.ADD_COMMENT;
                break;
            case 10:
                this.type = LawyerActivityType.READ_COMMENT;
                break;
            case 11:
                this.type = LawyerActivityType.READ_DOC;
                break;
            case 12:
                this.type = LawyerActivityType.DWONLOAD_DOC;
                break;
            case 13:
                this.type = LawyerActivityType.DELETE_DOC;
                break;
        }
    }
}
