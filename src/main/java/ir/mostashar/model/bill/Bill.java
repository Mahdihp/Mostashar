package ir.mostashar.model.bill;


import ir.mostashar.model.factor.Factor;
import ir.mostashar.model.wallet.Wallet;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "transactionnumber") // ایا باید یونیک باشد.
    private String transactionNumber;

    @Column(name = "trackingnumber") // ایا باید یونیک باشد.
    private String trackingNumber;

    @Column(name = "transactiondate")
    private Long transactionDate;

    @Column(name = "billstatus")
    private String billStatus;

    @Column(name = "billtype")
    private BillType billType;

    @Column(name = "value")
    private long value;

    @Column(name = "orguid")
    private String orgUid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billid", nullable = false)
    private Wallet wallet;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "bill")
    private Factor factor;


    public Bill() {
    }

}
