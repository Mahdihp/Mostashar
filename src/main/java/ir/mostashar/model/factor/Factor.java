package ir.mostashar.model.factor;

import ir.mostashar.model.bill.Bill;

import javax.persistence.*;
import java.util.UUID;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billid", referencedColumnName = "id")
    private Bill bill;

    public Factor() {
    }

    public Factor(UUID uid, String serviceDescription, String clientName, String clientCode, String address, Long tel, String postalCode, String factorNumber, Long creationDate, long value, boolean deleted) {
        this.uid = uid;
        this.serviceDescription = serviceDescription;
        this.clientName = clientName;
        this.clientCode = clientCode;
        this.address = address;
        this.tel = tel;
        this.postalCode = postalCode;
        this.factorNumber = factorNumber;
        this.creationDate = creationDate;
        this.value = value;
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
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

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getFactorNumber() {
        return factorNumber;
    }

    public void setFactorNumber(String factorNumber) {
        this.factorNumber = factorNumber;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
