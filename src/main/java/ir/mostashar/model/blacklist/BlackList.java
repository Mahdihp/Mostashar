package ir.mostashar.model.blacklist;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "blacklists")
public class BlackList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "creationdate")
    private Long creationDate;

    @Column(name = "sourceuid")
    private String sourceUid;

    @Column(name = "expirydate")
    private Long expiryDate;

    @Column(name = "description")
    private String description;

    public BlackList() {
    }

    public BlackList(UUID uid, Long creationDate, String sourceUid, Long expiryDate, String description) {
        this.uid = uid;
        this.creationDate = creationDate;
        this.sourceUid = sourceUid;
        this.expiryDate = expiryDate;
        this.description = description;
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

    public String getSourceUid() {
        return sourceUid;
    }

    public void setSourceUid(String sourceUid) {
        this.sourceUid = sourceUid;
    }

    public Long getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Long expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
