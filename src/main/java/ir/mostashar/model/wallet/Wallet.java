package ir.mostashar.model.wallet;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ir.mostashar.model.user.User;

@Entity
@Table(name = "wallets")
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private UUID uid;

	@Column(name = "value")
	private int value;

	@Column(name = "bankaccountname")
	private String bankAccountName;

	@Column(name = "bankaccountnumber")
	private String bankAccountNumber;

	@Column(name = "bankaccountsheba")
	private String bankAccountSheba;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
	
}
