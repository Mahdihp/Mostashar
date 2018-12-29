package ir.mostashar.model.notification;


import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "notification")
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    private String content;

    private String targetUid;

    private int type;

    private Long pushDate;

    private String notifParentUid;

    private boolean deleted = false;

    public Notifications() {
    }

    public Notifications(UUID uid, String content, String targetUid, int type, Long pushDate, String notifParentUid, boolean deleted) {
        this.uid = uid;
        this.content = content;
        this.targetUid = targetUid;
        this.type = type;
        this.pushDate = pushDate;
        this.notifParentUid = notifParentUid;
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTargetUid() {
        return targetUid;
    }

    public void setTargetUid(String targetUid) {
        this.targetUid = targetUid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getPushDate() {
        return pushDate;
    }

    public void setPushDate(Long pushDate) {
        this.pushDate = pushDate;
    }

    public String getNotifParentUid() {
        return notifParentUid;
    }

    public void setNotifParentUid(String notifParentUid) {
        this.notifParentUid = notifParentUid;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}

