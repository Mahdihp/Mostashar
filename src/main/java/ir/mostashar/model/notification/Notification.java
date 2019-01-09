package ir.mostashar.model.notification;


import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.mostashar.model.reminder.Reminder;
import ir.mostashar.model.user.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "content")
    private String content;

    @Column(name = "targetuid")
    private String targetUid;

    @Column(name = "type")
    private int type;

    @Column(name = "pushdate")
    private Long pushDate;

    @Column(name = "notifParentuid")
    private String notifParentUid;

    @Column(name = "deleted")
    private boolean deleted = false;


    @OneToMany(mappedBy = "notification")
    private Set<Reminder> reminders = new HashSet<>();

    public Notification() {
    }

    public Notification(UUID uid, String content, String targetUid, int type, Long pushDate, String notifParentUid, boolean deleted) {
        this.uid = uid;
        this.content = content;
        this.targetUid = targetUid;
        this.type = type;
        this.pushDate = pushDate;
        this.notifParentUid = notifParentUid;
        this.deleted = deleted;
    }

    public Set<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(Set<Reminder> reminders) {
        this.reminders = reminders;
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

