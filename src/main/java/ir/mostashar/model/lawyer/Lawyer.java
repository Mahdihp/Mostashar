package ir.mostashar.model.lawyer;

import ir.mostashar.model.acceptRequest.AcceptRequest;
import ir.mostashar.model.activity.ActivityLawyer;
import ir.mostashar.model.adviceType.AdviceType;
import ir.mostashar.model.answer.Answer;
import ir.mostashar.model.calls.Call;
import ir.mostashar.model.doc.Doc;
import ir.mostashar.model.expertise.Expertise;
import ir.mostashar.model.failRequest.FailRequest;
import ir.mostashar.model.officeAddress.OfficeAddress;
import ir.mostashar.model.organization.Organization;
import ir.mostashar.model.packsnapshot.PackSnapshot;
import ir.mostashar.model.presenceSchedule.PresenceSchedule;
import ir.mostashar.model.user.User;
import lombok.Data;

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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizationid")
    private Organization organization;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    private Set<FailRequest> failRequests;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    private Set<AcceptRequest> acceptRequests;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    private Set<ActivityLawyer> activities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    private Set<Call> calls;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    private Set<Doc> docs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    private Set<Answer> answers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advicetype", nullable = false)
    private AdviceType advicetype;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lawyer")
    private Set<PackSnapshot> packSnapshots;

    public Lawyer() {
    }


}
