package ir.mostashar.model.doc;

import ir.mostashar.model.activity.LawyerActivity;
import ir.mostashar.model.call.Call;
import ir.mostashar.model.file.File;
import ir.mostashar.model.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@ToString
@Data
@Entity
@Table(name = "docs")
public class Doc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "checksum")
    private String checksum;

    @Column(name = "doctype")
    private DocType docType;

    @Column(name = "mimetype")
    private MimeType mimeType;

    @Column(name = "hashcode")
    private String hashCode;

    @Lob
    @Column(name="data")
    private byte[] data;

    @Column(name = "creationdate")
    private Long creationDate;

    @Column(name = "deleted")
    private boolean deleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    @EqualsAndHashCode.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "callid")
    private Call call;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "doc")
    private Set<LawyerActivity> activities = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fileid")
    @EqualsAndHashCode.Exclude
    private File file;

    public Doc() {
    }

}
