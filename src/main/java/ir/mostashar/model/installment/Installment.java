package ir.mostashar.model.installment;

import ir.mostashar.model.consumptionPack.ConsumptionPack;
import ir.mostashar.model.factor.Factor;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "installments")
public class Installment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "installmentnumber")
    private Integer installmentNumber;

    @Column(name = "installmenttotalnumber")
    private Integer installmentTotalNumber;

    @Column(name = "creationdate")
    private Long creationDate;

    @Column(name = "value")
    private long value;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumptionpackid", nullable = false)
    private ConsumptionPack consumptionPack;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "installment")
    private Factor userProfile;


    public Installment() {
    }
}
