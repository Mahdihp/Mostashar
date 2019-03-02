package ir.mostashar.model.notification.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.mostashar.model.BaseDTO;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationDTO extends BaseDTO {


    private String id;
    private String content;
    //    private String targetUid;
    private Integer type;
    private Long creationDate;
    private String notifParentUid;
    private Boolean deleted = false;
    private String requestId;

    public NotificationDTO() {
    }
}
