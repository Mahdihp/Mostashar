package ir.mostashar.model.file;

import ir.mostashar.model.activity.Activity;
import ir.mostashar.model.client.Client;
import ir.mostashar.model.doc.Doc;
import ir.mostashar.model.request.Request;
import ir.mostashar.model.sharingPerspective.SharingPerspectives;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
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

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "file")
    private Set<Request> requests = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "file")
    private Set<Doc> docs = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "file")
    private Set<SharingPerspectives> sharingPerspectives = new HashSet<>();


    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "file")
    private Set<Activity> activities = new HashSet<>();

    public File() {
    }

    public File(UUID uid, String fileNumber, String title, String description, Long creationDate, Long modificationDate, Client client, Set<Request> requests, Set<Doc> docs, Set<SharingPerspectives> sharingPerspectives, Set<Activity> activities) {
        this.uid = uid;
        this.fileNumber = fileNumber;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.client = client;
        this.requests = requests;
        this.docs = docs;
        this.sharingPerspectives = sharingPerspectives;
        this.activities = activities;
    }
}
