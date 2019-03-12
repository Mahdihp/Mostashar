package ir.mostashar.model.lawyer;

import ir.mostashar.model.acceptRequest.AcceptRequest;
import ir.mostashar.model.activity.LawyerActivity;
import ir.mostashar.model.adminConfirmation.AdminConfirmation;
import ir.mostashar.model.adviceType.AdviceType;
import ir.mostashar.model.answer.Answer;
import ir.mostashar.model.call.Call;
import ir.mostashar.model.doc.Doc;
import ir.mostashar.model.expertise.Expertise;
import ir.mostashar.model.failRequest.FailRequest;
import ir.mostashar.model.feedback.FeedBack;
import ir.mostashar.model.officeAddress.OfficeAddress;
import ir.mostashar.model.organization.Organization;
import ir.mostashar.model.packsnapshot.PackSnapshot;
import ir.mostashar.model.presenceSchedule.PresenceSchedule;
import ir.mostashar.model.rightMessage.RightMessage;
import ir.mostashar.model.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "lawyers")
public class Lawyer extends User {

    private static final long serialVersionUID = 1L;

    @Column(name = "available")
    private Boolean available = false;

    @Column(name = "level")
    private Integer level = 1;

    @Column(name = "priceperminute")
    private Integer pricePerMinute = 1;

    @Column(name = "verified")
    private Boolean verified = false;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<OfficeAddress> officeAddresses = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Answer> answer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<PresenceSchedule> presenceSchedules = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "lawyer_expertise",
            joinColumns = {@JoinColumn(name = "lawyerid")},
            inverseJoinColumns = {@JoinColumn(name = "expertiseid")})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Expertise> expertises = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizationid")
    private Organization organization;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    @EqualsAndHashCode.Exclude
    private Set<FailRequest> failRequests= new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<AcceptRequest> acceptRequests= new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    private Set<LawyerActivity> activities= new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    private Set<Call> calls= new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    private Set<Doc> docs= new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    private Set<Answer> answers= new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    private Set<AdminConfirmation> adminConfirmations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    @EqualsAndHashCode.Exclude
    private Set<FeedBack> feedBacks= new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advicetype", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private AdviceType advicetype;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    private Set<PackSnapshot> packSnapshots= new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    @EqualsAndHashCode.Exclude
    private Set<RightMessage> rightMessages= new HashSet<>();

    public Lawyer() {
    }


}
