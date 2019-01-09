package ir.mostashar.model.client;

import ir.mostashar.model.AuditModel;
import ir.mostashar.model.calls.Call;
import ir.mostashar.model.feedback.Feedback;
import ir.mostashar.model.file.File;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.question.Question;
import ir.mostashar.model.request.Request;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


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


    public Set<Call> getCalls() {
        return calls;
    }

    public void setCalls(Set<Call> calls) {
        this.calls = calls;
    }

    public Set<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Set<Lawyer> getLawyers() {
        return lawyers;
    }

    public void setLawyers(Set<Lawyer> lawyers) {
        this.lawyers = lawyers;
    }

    public Set<Request> getRequests() {
        return requests;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }

    public Set<File> getFiles() {
        return files;
    }

    public void setFiles(Set<File> files) {
        this.files = files;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }
}
