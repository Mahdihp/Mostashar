package ir.mostashar.model.factor;

import ir.mostashar.model.bill.Bill;
import ir.mostashar.model.installment.Installment;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "factors")
public class Factor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "servicedescription")
    private String serviceDescription;

    @Column(name = "clientName")
    private String clientName;

    @Column(name = "clientcode")
    private String clientCode;

    @Column(name = "address")
    private String address;

    @Column(name = "tel")
    private Long tel;

    @Column(name = "postalcode")
    private String postalCode;

    @Column(name = "factornumber")
    private String factorNumber;

    @Column(name = "creationdate")
    private Long creationDate;

    @Column(name = "value")
    private long value;

    @Column(name = "deleted")
    private boolean deleted = false;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "billid", nullable = false)
    private Bill bill;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "installmentid", nullable = false)
    private Installment installment;

    public Factor() {
    }

}
