package ir.mostashar.model.wallet;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;

import ir.mostashar.model.bill.Bill;
import ir.mostashar.model.organization.Organization;
import ir.mostashar.model.user.User;
import lombok.Data;

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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "wallet")
	private Set<Bill> bills = new HashSet<>();

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(nullable = false)
	private User user;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(nullable = false)
	private Organization organization;

	public Wallet() {
	}

}