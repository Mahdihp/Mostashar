package ir.mostashar.model.notification.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

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
}
