package ir.mostashar.model.bill;


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

    @Column(name = "transactionnumber")
    private String transactionNumber;

    @Column(name = "trackingnumber")
    private String trackingNumber;

    @Column(name = "transactiondate")
    private Long transactionDate;

    @Column(name = "status")
    private String status;

    @Column(name = "value")
    private long value;

    @Column(name = "orguid")
    private String orgUid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billid", nullable = false)
    private Wallet wallet;


    public Bill() {
    }
}
