package ir.mostashar.model.notification.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
@Data
public class NotificationForm {

    private String uid;

    @NotBlank
    private String content;
//    private String targetUid;
    private int type;
    private Long creationDate;
    private String notifParentUid;
    private boolean deleted = false;

    @NotBlank
    private String requestId;

    public NotificationForm() {
    }

    public NotificationForm(@NotBlank String content, Long creationDate, @NotBlank String requestId) {
        this.content = content;
        this.creationDate = creationDate;
        this.requestId = requestId;
    }
}
