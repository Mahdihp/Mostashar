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

    @Column(name = "password")
    private String password;

    @Column(name = "creationdate")
    private Long creationDate;

    @Column(name = "expirydate")
    private Long expiryDate;

    @Column(name = "percentorgstock")
    private int percentLawyerStock; // درصد سهم پیش فرض مشاور

    @Column(name = "percentorgstock")
    private int percentOrgStock; // درصد سهم خود سازمان

    @Column(name = "percentmasterstock")
    private int percentMasterStock; // درصد سهم سازمان بالادستی(مستشار)

    @Column(name = "verified")
    private boolean verified = false;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
    private Set<Lawyer> lawyers=new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "organization")
    private Wallet wallet;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "masterid")
    private Organization master;

    @OneToMany(mappedBy = "master")
    private Set<Organization> subordinates = new HashSet<>();

    public Organization() {
    }
}
