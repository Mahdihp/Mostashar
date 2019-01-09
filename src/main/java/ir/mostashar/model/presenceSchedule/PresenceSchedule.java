package ir.mostashar.model.presenceSchedule;

import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.officeAddress.OfficeAddress;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "presenceschedules")
public class PresenceSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "specialdate")
    private Long specialDate;

    @Column(name = "time")
    private Long time;

    @Column(name = "weekday")
    private int weekDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "officesaddressid",nullable = false)
    private OfficeAddress officesaddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lawyerid",nullable = false)
    private Lawyer lawyer;

    public PresenceSchedule() {
    }

    public Lawyer getLawyer() {
        return lawyer;
    }

    public void setLawyer(Lawyer lawyer) {
        this.lawyer = lawyer;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSpecialDate() {
        return specialDate;
    }

    public void setSpecialDate(Long specialDate) {
        this.specialDate = specialDate;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public OfficeAddress getOfficesaddress() {
        return officesaddress;
    }

    public void setOfficesaddress(OfficeAddress officesaddress) {
        this.officesaddress = officesaddress;
    }
}
