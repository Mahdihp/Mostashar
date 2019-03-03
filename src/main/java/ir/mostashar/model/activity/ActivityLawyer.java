package ir.mostashar.model.activity;
import ir.mostashar.model.doc.Doc;
import ir.mostashar.model.file.File;
import ir.mostashar.model.lawyer.Lawyer;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "activitylawyers")
public class ActivityLawyer {

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

    public ActivityLawyer() {
    }

}