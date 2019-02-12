package ir.mostashar.model.client;

import ir.mostashar.model.calls.Call;
import ir.mostashar.model.feedback.Feedback;
import ir.mostashar.model.file.File;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.question.Question;
import ir.mostashar.model.request.Request;
import ir.mostashar.model.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@EqualsAndHashCode
@Data
@Entity
@Table(name = "clients")
public class Client extends User {

    private static final long serialVersionUID = 1L;

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
    private Set<Question> questions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<Feedback> feedbacks = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<Request> requests = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    @EqualsAndHashCode.Exclude
    private Set<File> files = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<Call> calls = new HashSet<>();

    public Client() {
    }

    public Client(Long tel) {
        this.tel = tel;
    }
}
