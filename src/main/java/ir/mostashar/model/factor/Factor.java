package ir.mostashar.model.factor;

import ir.mostashar.model.bill.Bill;
import ir.mostashar.model.installment.Installment;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "factors") // فاکتورها
public class Factor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "servicedescription") // توضیحی ثابت و خبری از خرید اون ایتم
    private String serviceDescription; // مقدار این از کجا پر میشه

    @Column(name = "clientName")
    private String clientName; // این برای چیه

    @Column(name = "address")
    private String address;

    @Column(name = "tel")
    private Long tel;

    @Column(name = "postalcode")
    private String postalCode;

    @Column(name = "factornumber", unique = true, nullable = false)
    private Long factorNumber;

    @Column(name = "creationdate")
    private Long creationDate;

    @Column(name = "value")
    private long value;

    @Column(name = "deleted")
    private boolean deleted = false;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "billid") // پرسیده شود ایا نال پذیر باشد یا خیر
    private Bill bill;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "factor")
    private Installment installment;

    public Factor() {
    }

}
