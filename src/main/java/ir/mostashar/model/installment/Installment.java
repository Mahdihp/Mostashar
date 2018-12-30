package ir.mostashar.model.installment;

import javax.persistence.*;
import java.util.UUID;

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

    public Installment() {
    }

    public Installment(UUID uid, Integer installmentNumber, Integer installmentTotalNumber, Long creationDate, long value) {
        this.uid = uid;
        this.installmentNumber = installmentNumber;
        this.installmentTotalNumber = installmentTotalNumber;
        this.creationDate = creationDate;
        this.value = value;
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

    public Integer getInstallmentNumber() {
        return installmentNumber;
    }

    public void setInstallmentNumber(Integer installmentNumber) {
        this.installmentNumber = installmentNumber;
    }

    public Integer getInstallmentTotalNumber() {
        return installmentTotalNumber;
    }

    public void setInstallmentTotalNumber(Integer installmentTotalNumber) {
        this.installmentTotalNumber = installmentTotalNumber;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
