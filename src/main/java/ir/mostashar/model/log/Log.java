package ir.mostashar.model.log;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "logs")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "creationdate")
    private Long creationDate;

    @Column(name = "message")
    private String message;

    @Column(name = "details")
    private String details;

    @Column(name = "sourceuid")
    private String sourceUid;

    @Column(name = "sourcetype")
    private int sourceType;

    @Column(name = "targetuid")
    private String targetUid;

    @Column(name = "targettype")
    private int targetType;

    public Log() {
    }

    public Log(UUID uid, Long creationDate, String message, String details, String sourceUid, int sourceType, String targetUid, int targetType) {
        this.uid = uid;
        this.creationDate = creationDate;
        this.message = message;
        this.details = details;
        this.sourceUid = sourceUid;
        this.sourceType = sourceType;
        this.targetUid = targetUid;
        this.targetType = targetType;
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

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSourceUid() {
        return sourceUid;
    }

    public void setSourceUid(String sourceUid) {
        this.sourceUid = sourceUid;
    }

    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }

    public String getTargetUid() {
        return targetUid;
    }

    public void setTargetUid(String targetUid) {
        this.targetUid = targetUid;
    }

    public int getTargetType() {
        return targetType;
    }

    public void setTargetType(int targetType) {
        this.targetType = targetType;
    }
}
