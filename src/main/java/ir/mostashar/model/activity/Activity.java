package ir.mostashar.model.activity;
import ir.mostashar.model.doc.Doc;
import ir.mostashar.model.file.File;
import ir.mostashar.model.lawyer.Lawyer;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "type")
    private int type;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "creationdate")
    private Long creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lawyerid",nullable = true)
    private Lawyer lawyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "docid", nullable = true)
    private Doc doc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fileid", nullable = true)
    private File file;

    public Activity() {
    }

    public Activity(UUID uid, int type, String title, String description, Long creationDate, Lawyer lawyer, Doc doc, File file) {
        this.uid = uid;
        this.type = type;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.lawyer = lawyer;
        this.doc = doc;
        this.file = file;
    }
}
