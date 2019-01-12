package ir.mostashar.model.organization;

import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.wallet.Wallet;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "tel")
    private String tel;

    @Column(name = "terminalid")
    private long terminalId;

    @Column(name = "username")
    private String username;

    @Column(name = "userpassword")
    private String userPassword;

    @Column(name = "creationdate")
    private Long creationDate;

    @Column(name = "expirydate")
    private Long expiryDate;

    @Column(name = "orgstock")
    private Long orgStock;

    @Column(name = "appstock")
    private Long appStock;

    @Column(name = "verified")
    private boolean verified = false;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
    private Set<Lawyer> lawyers=new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "organization")
    private Wallet wallet;

    public Organization() {
    }

    public Organization(UUID uid, String name, String description, String address, String tel, long terminalId, String username, String userPassword, Long creationDate, Long expiryDate, Long orgStock, Long appStock, boolean verified) {
        this.uid = uid;
        this.name = name;
        this.description = description;
        this.address = address;
        this.tel = tel;
        this.terminalId = terminalId;
        this.username = username;
        this.userPassword = userPassword;
        this.creationDate = creationDate;
        this.expiryDate = expiryDate;
        this.orgStock = orgStock;
        this.appStock = appStock;
        this.verified = verified;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public long getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(long terminalId) {
        this.terminalId = terminalId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public Long getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Long expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Long getOrgStock() {
        return orgStock;
    }

    public void setOrgStock(Long orgStock) {
        this.orgStock = orgStock;
    }

    public Long getAppStock() {
        return appStock;
    }

    public void setAppStock(Long appStock) {
        this.appStock = appStock;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
