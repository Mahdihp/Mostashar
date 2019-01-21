package ir.mostashar.model.client;

import ir.mostashar.model.AuditModel;
import ir.mostashar.model.calls.Call;
import ir.mostashar.model.feedback.Feedback;
import ir.mostashar.model.file.File;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.question.Question;
import ir.mostashar.model.request.Request;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "clients")
public class Client extends AuditModel {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "jobtitle")
    private String jobTitle;

    @Column(name = "address")
    private String address;

    @Column(name = "postalcode")
    private String postalCode;

    @Column(name = "fieldofstudy")
    private String fieldOfStudy;

    @Column(name = "tel")
    private Long tel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<Question> questions= new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<Feedback> feedbacks= new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "client_lawyer",
            joinColumns = { @JoinColumn(name = "clientid") },
            inverseJoinColumns = { @JoinColumn(name = "lawyerid") })
    private Set<Lawyer> lawyers= new HashSet<>();


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<Request> requests= new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<File> files= new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "client")
    private Set<Call> calls=new HashSet<>();

    public Client() {
    }

    public Client(UUID uid, String jobTitle, String address, String postalCode, String fieldOfStudy, Long tel, Set<Question> questions, Set<Feedback> feedbacks, Set<Lawyer> lawyers, Set<Request> requests, Set<File> files, Set<Call> calls) {
        this.uid = uid;
        this.jobTitle = jobTitle;
        this.address = address;
        this.postalCode = postalCode;
        this.fieldOfStudy = fieldOfStudy;
        this.tel = tel;
        this.questions = questions;
        this.feedbacks = feedbacks;
        this.lawyers = lawyers;
        this.requests = requests;
        this.files = files;
        this.calls = calls;
    }
}
