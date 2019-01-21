package ir.mostashar.model.lawyer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.mostashar.model.AuditModel;
import ir.mostashar.model.acceptRequest.AcceptRequest;
import ir.mostashar.model.accessentry.AccessEntry;
import ir.mostashar.model.activity.Activity;
import ir.mostashar.model.adviceType.AdviceType;
import ir.mostashar.model.answer.Answer;
import ir.mostashar.model.calls.Call;
import ir.mostashar.model.client.Client;
import ir.mostashar.model.doc.Doc;
import ir.mostashar.model.expertise.Expertise;
import ir.mostashar.model.failRequest.FailRequest;
import ir.mostashar.model.officeAddress.OfficeAddress;
import ir.mostashar.model.organization.Organization;
import ir.mostashar.model.presenceSchedule.PresenceSchedule;
import ir.mostashar.model.wallet.Wallet;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "lawyers")
public class Lawyer extends AuditModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "isavailable")
    private boolean isAvailable = false;

    @Column(name = "level")
    private int level = 1;

    @Column(name = "verified")
    private boolean verified = false;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "lawyers")
    private Set<Client> client = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    private Set<OfficeAddress> officeAddresses = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    private Set<Answer> answer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    private Set<PresenceSchedule> presenceSchedules = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "lawyer_expertise",
            joinColumns = {@JoinColumn(name = "lawyerid")},
            inverseJoinColumns = {@JoinColumn(name = "expertiseid")})
    private Set<Expertise> expertises = new HashSet<>();

    // ارتباط یک طرفه با کلاس Lawyer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "failRequestid", nullable = false)
    private FailRequest failRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizationid", nullable = false)
    private Organization organization;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    private Set<AcceptRequest> acceptRequests;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    private Set<Activity> activities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    private Set<Call> calls;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    private Set<Doc> docs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    private Set<Answer> answers;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "advicetypeid", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private AdviceType adviceType;

    public Lawyer() {
    }

    public Lawyer(UUID uid, boolean isAvailable, int level, boolean verified, Set<Answer> answer, Set<Client> client, Set<OfficeAddress> officeAddresses, Set<PresenceSchedule> presenceSchedules, Set<Expertise> expertises, Organization organization, Set<AcceptRequest> acceptRequests, Set<Activity> activities, Set<Call> calls, Set<Doc> docs, Set<Answer> answers, AdviceType adviceType, FailRequest failRequest) {
        this.uid = uid;
        this.isAvailable = isAvailable;
        this.level = level;
        this.verified = verified;
        this.answer = answer;
        this.client = client;
        this.officeAddresses = officeAddresses;
        this.presenceSchedules = presenceSchedules;
        this.expertises = expertises;
        this.organization = organization;
        this.acceptRequests = acceptRequests;
        this.activities = activities;
        this.calls = calls;
        this.docs = docs;
        this.answers = answers;
        this.adviceType = adviceType;
        this.failRequest = failRequest;
    }
}
