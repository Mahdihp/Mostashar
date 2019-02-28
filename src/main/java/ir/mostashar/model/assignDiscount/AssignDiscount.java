package ir.mostashar.model.assignDiscount;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	private boolean active = false;

	@Column(name = "creationdate")
	@CreatedDate
	private Long creationDate;

	@Column(name = "modificationdate")
	@CreatedDate
	private Long modificationDate;

	@Column(name = "expirydate")
	@CreatedDate
	private Long expiryDate;

	@ManyToOne
	@JoinColumn( nullable=false)
    @JsonIgnore
    private User user;

	@ManyToOne
    @JoinColumn(name="discountpackid", nullable=false)
    private DiscountPack discountPack;

	public AssignDiscount() {
	}

}
