package ir.mostashar.model.doc;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "doc")
public class Doc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "checksum")
    private String checksum;

    @Column(name = "hashcode")
    private String hashCode;

    @Column(name = "doctype")
    private String docType;

    @Column(name = "creationdate")
    private Long creationDate;

    public Doc() {
    }

    public Doc(UUID uid, String checksum, String hashCode, String docType, Long creationDate) {
        this.uid = uid;
        this.checksum = checksum;
        this.hashCode = hashCode;
        this.docType = docType;
        this.creationDate = creationDate;
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

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }
}
