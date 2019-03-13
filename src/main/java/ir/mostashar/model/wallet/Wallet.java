package ir.mostashar.model.wallet;

import ir.mostashar.model.bill.Bill;
import ir.mostashar.model.organization.Organization;
import ir.mostashar.model.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "value")
    private int value;

    @Column(name = "bankaccountname")
    private String bankAccountName;

    @Column(name = "bankaccountnumber")
    private String bankAccountNumber;

    @Column(name = "bankaccountsheba")
    private String bankAccountSheba;

    @Column(name = "deleted")
    private boolean deleted = false;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wallet")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Bill> bills = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userid", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizationid")
    private Organization organization;

    public Wallet() {
    }

}