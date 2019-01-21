package ir.mostashar.model.log;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
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
}
