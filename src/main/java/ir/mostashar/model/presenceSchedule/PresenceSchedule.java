package ir.mostashar.model.presenceSchedule;

import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.officeAddress.OfficeAddress;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;


@Data
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
    @JoinColumn(name = "officesaddressid", nullable = false)
    private OfficeAddress officesaddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lawyerid", nullable = false)
    private Lawyer lawyer;

    public PresenceSchedule() {
    }

    public PresenceSchedule(UUID uid, String title, String description, Long specialDate, Long time, int weekDay, OfficeAddress officesaddress, Lawyer lawyer) {
        this.uid = uid;
        this.title = title;
        this.description = description;
        this.specialDate = specialDate;
        this.time = time;
        this.weekDay = weekDay;
        this.officesaddress = officesaddress;
        this.lawyer = lawyer;
    }
}