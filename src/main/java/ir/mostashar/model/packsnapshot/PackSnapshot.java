package ir.mostashar.model.packsnapshot;

import ir.mostashar.model.adviceType.AdviceType;
import ir.mostashar.model.consumptionPack.ConsumptionPack;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.pack.Pack;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "packsnapshots")
public class PackSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "packname")
    private String packName;

    @Column(name = "packdescription")
    private String packDescription;

    @Column(name = "packminute")
    private int packMinute;

    @Column(name = "lawyerpriceperminute")
    private int lawyerPricePerMinute;

    @Column(name = "totalprice")
    private int totalPrice;


    @Column(name = "active")
    private boolean active = false;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "pack")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<ConsumptionPack> consumptionpacks = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advicetypeid", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private AdviceType advicetype;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lawyerid", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Lawyer lawyer;


    public PackSnapshot() {
    }


}
