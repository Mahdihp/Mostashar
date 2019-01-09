package ir.mostashar.model.wallet;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;

import ir.mostashar.model.bill.Bill;
import ir.mostashar.model.user.User;

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
	private Set<Bill> bills=new HashSet<>();

	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private User user;

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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getBankAccountSheba() {
		return bankAccountSheba;
	}

	public void setBankAccountSheba(String bankAccountSheba) {
		this.bankAccountSheba = bankAccountSheba;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
