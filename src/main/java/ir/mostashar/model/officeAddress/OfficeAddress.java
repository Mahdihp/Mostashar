package ir.mostashar.model.officeAddress;

import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.presenceSchedule.PresenceSchedule;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
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

    public OfficeAddress(UUID uid) {
        this.uid = uid;
    }

}
