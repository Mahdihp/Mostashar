package ir.mostashar.model.notification.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString
@Data
public class NotificationForm {

    private String uid;

    @NotNull
    private String content;
//    private String targetUid;
private Integer type;
    private Long creationDate;
    private String notifParentUid;
    private Boolean deleted;

    @NotNull
    private String requestId;

    public NotificationForm() {
    }

    public NotificationForm(@NotNull String content, Long creationDate, @NotNull String requestId) {
        this.content = content;
        this.creationDate = creationDate;
        this.requestId = requestId;
    }
}
