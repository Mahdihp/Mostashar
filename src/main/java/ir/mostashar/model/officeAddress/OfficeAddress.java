package ir.mostashar.model.officeAddress;

import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.presenceSchedule.PresenceSchedule;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "officesaddress")
public class OfficeAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "title")
    private String title;

    @Column(name = "address")
    private String address;

    @Column(name = "tel")
    private Long tel;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lawyerid", nullable = false)
    private Lawyer lawyer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "officesaddress")
    private Set<PresenceSchedule> presenceSchedule = new HashSet<>();

    public OfficeAddress() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Lawyer getLawyer() {
        return lawyer;
    }

    public void setLawyer(Lawyer lawyer) {
        this.lawyer = lawyer;
    }

    public Set<PresenceSchedule> getPresenceSchedule() {
        return presenceSchedule;
    }

    public void setPresenceSchedule(Set<PresenceSchedule> presenceSchedule) {
        this.presenceSchedule = presenceSchedule;
    }
}
