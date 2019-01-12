package ir.mostashar.model.lawyer;

import ir.mostashar.model.AuditModel;
import ir.mostashar.model.answer.Answer;
import ir.mostashar.model.client.Client;
import ir.mostashar.model.expertise.Expertise;
import ir.mostashar.model.failRequest.FailRequest;
import ir.mostashar.model.officeAddress.OfficeAddress;
import ir.mostashar.model.presenceSchedule.PresenceSchedule;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    private Set<Answer> answer;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "lawyers")
    private Set<Client> client =new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    private Set<OfficeAddress> officeAddresses=new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    private Set<PresenceSchedule> presenceSchedules=new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "lawyer_expertise",
            joinColumns = { @JoinColumn(name = "lawyerid") },
            inverseJoinColumns = { @JoinColumn(name = "expertiseid") })
    private Set<Expertise> expertises =new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="failrequestid", nullable=false)
    private FailRequest failRequest;

    public Lawyer() {
    }


    public FailRequest getFailRequest() {
        return failRequest;
    }

    public void setFailRequest(FailRequest failRequest) {
        this.failRequest = failRequest;
    }

    public Set<Expertise> getExpertises() {
        return expertises;
    }

    public void setExpertises(Set<Expertise> expertises) {
        this.expertises = expertises;
    }

    public Set<PresenceSchedule> getPresenceSchedules() {
        return presenceSchedules;
    }

    public void setPresenceSchedules(Set<PresenceSchedule> presenceSchedules) {
        this.presenceSchedules = presenceSchedules;
    }

    public Set<OfficeAddress> getOfficeAddresses() {
        return officeAddresses;
    }

    public void setOfficeAddresses(Set<OfficeAddress> officeAddresses) {
        this.officeAddresses = officeAddresses;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Set<Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(Set<Answer> answer) {
        this.answer = answer;
    }

    public Set<Client> getClient() {
        return client;
    }

    public void setClient(Set<Client> client) {
        this.client = client;
    }
}
