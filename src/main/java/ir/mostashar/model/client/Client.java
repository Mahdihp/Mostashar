package ir.mostashar.model.client;

import ir.mostashar.model.call.Call;
import ir.mostashar.model.feedback.FeedBack;
import ir.mostashar.model.file.File;
import ir.mostashar.model.question.Question;
import ir.mostashar.model.request.Request;
import ir.mostashar.model.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


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

    @Column(name = "fieldofstudy") //رشته تحصیلی
    private String fieldOfStudy;

    @Column(name = "tel")
    private Long tel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    @EqualsAndHashCode.Exclude
    private Set<Question> questions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    @EqualsAndHashCode.Exclude
    private Set<Request> requests = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<File> files = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    @EqualsAndHashCode.Exclude
    private Set<Call> calls = new HashSet<>();

    public Client() {
    }

    public Client(Long tel) {
        this.tel = tel;
    }
}
