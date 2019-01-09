package ir.mostashar.model.file;

import ir.mostashar.model.client.Client;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "filenumber")
    private String fileNumber;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "creationDate")
    private Long creationDate;

    @Column(name = "modificationdate")
    private Long modificationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientid",nullable = false)
    private Client client;

    public File() {
    }

    public File(UUID uid, String fileNumber, String title, String description, Long creationDate, Long modificationDate) {
        this.uid = uid;
        this.fileNumber = fileNumber;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
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

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public Long getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Long modificationDate) {
        this.modificationDate = modificationDate;
    }
}
