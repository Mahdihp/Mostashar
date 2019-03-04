package ir.mostashar.model.doc;

import ir.mostashar.model.activity.ActivityLawyer;
import ir.mostashar.model.call.Call;
import ir.mostashar.model.file.File;
import ir.mostashar.model.lawyer.Lawyer;
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

    @Column(name = "hashcode")
    private String hashCode;

    @Column(name = "doctype")
    private DocType docType;

    @Lob
    @Column(name="data")
    private byte[] data;

    @Column(name = "creationdate")
    private Long creationDate;

    @Column(name = "deleted")
    private boolean deleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lawyerid")
    @EqualsAndHashCode.Exclude
    private Lawyer lawyer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "callid")
    private Call call;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "doc")
    private Set<ActivityLawyer> activities = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fileid", nullable = false)
    @EqualsAndHashCode.Exclude
    private File file;

    public Doc() {
    }

}
