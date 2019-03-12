package ir.mostashar.model.file;

import ir.mostashar.model.activity.LawyerActivity;
import ir.mostashar.model.client.Client;
import ir.mostashar.model.doc.Doc;
import ir.mostashar.model.request.Request;
import ir.mostashar.model.sharingPerspective.SharingPerspectives;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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

    @Column(name = "filenumber", unique = true, nullable = false)
    private Long fileNumber;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "creationDate")
    private Long creationDate;

    @Column(name = "modificationdate")
    private Long modificationDate;

    @Column(name = "deleted")
    private boolean deleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientid",nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Client client;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "file")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
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
    private Set<LawyerActivity> activities = new HashSet<>();

    public File() {
    }

}
