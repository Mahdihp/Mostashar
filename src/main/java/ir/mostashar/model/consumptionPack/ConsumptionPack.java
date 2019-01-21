package ir.mostashar.model.consumptionPack;

import ir.mostashar.model.installment.Installment;
import ir.mostashar.model.pack.Pack;
import ir.mostashar.model.request.Request;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "consumptionpacks")
public class ConsumptionPack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "consumptiontime")
    private Long consumptionTime;

    @Column(name = "value")
    private long value;

    @Column(name = "type")
    private int type;

    @Column(name = "firstinstallmentdate")
    private Long firstInstallmentDate;

    @Column(name = "lastinstallmentdate")
    private Long lastInstallmentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "packid", nullable = false)
    private Pack pack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requestid", nullable = false)
    private Request request;

//    @OneToMany(cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY,
//            mappedBy = "consumptionpack")
//    private Set<Installment> comments = new HashSet<>();

    public ConsumptionPack() {
    }

    public ConsumptionPack(UUID uid, Long consumptionTime, long value, int type, Long firstInstallmentDate, Long lastInstallmentDate) {
        this.uid = uid;
        this.consumptionTime = consumptionTime;
        this.value = value;
        this.type = type;
        this.firstInstallmentDate = firstInstallmentDate;
        this.lastInstallmentDate = lastInstallmentDate;
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

    public Long getConsumptionTime() {
        return consumptionTime;
    }

    public void setConsumptionTime(Long consumptionTime) {
        this.consumptionTime = consumptionTime;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getFirstInstallmentDate() {
        return firstInstallmentDate;
    }

    public void setFirstInstallmentDate(Long firstInstallmentDate) {
        this.firstInstallmentDate = firstInstallmentDate;
    }

    public Long getLastInstallmentDate() {
        return lastInstallmentDate;
    }

    public void setLastInstallmentDate(Long lastInstallmentDate) {
        this.lastInstallmentDate = lastInstallmentDate;
    }
}
