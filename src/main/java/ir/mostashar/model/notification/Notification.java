package ir.mostashar.model.notification;


import ir.mostashar.model.reminder.Reminder;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
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
}

