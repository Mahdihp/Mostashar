package ir.mostashar.model.assignDiscount;

import java.util.UUID;

import javax.persistence.*;

import ir.mostashar.model.discountPack.DiscountPack;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ir.mostashar.model.user.User;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

@Data
@Entity
@Table(name = "assigndiscounts")
public class AssignDiscount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true , nullable = false)
	private UUID uid;

	@Column(name = "active")
	private Boolean active = false;

	@Column(name = "codeoff")
	private String codeOff;

	@Column(name = "creationdate")
	@CreatedDate
	private Long creationDate;

	@Column(name = "modificationdate")
	@CreatedDate
	private Long modificationDate;

	@Column(name = "expirydate")
	@CreatedDate
	private Long expiryDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", nullable = false)
    private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "discountpackid", nullable = false)
    private DiscountPack discountpack;

	public AssignDiscount() {
	}

}
