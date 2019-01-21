package ir.mostashar.model.adminConfirmation;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "adminconfirmations")
public class AdminConfirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "targetuid")
    private String targetUid;

    @Column(name = "targettype")
    private int targetType;

    @Column(name = "verified")
    private boolean verified;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "creationdate")
    private Long creationDate;

    public AdminConfirmation() {
    }

    public AdminConfirmation(UUID uid, String title, String description, String targetUid, int targetType, boolean verified, boolean deleted, Long creationDate) {
        this.uid = uid;
        this.title = title;
        this.description = description;
        this.targetUid = targetUid;
        this.targetType = targetType;
        this.verified = verified;
        this.deleted = deleted;
        this.creationDate = creationDate;
    }
}