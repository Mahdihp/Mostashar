package ir.mostashar.model.organization;

import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.wallet.Wallet;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
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

    public Organization(UUID uid, String name, String description, String address, String tel, long terminalId, String username, String userPassword, Long creationDate, Long expiryDate, Long orgStock, Long appStock, boolean verified, Set<Lawyer> lawyers, Wallet wallet) {
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
        this.lawyers = lawyers;
        this.wallet = wallet;
    }
}
