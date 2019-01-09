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
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "assigndiscounts")
public class AssignDiscount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true , nullable = false)
	private UUID uid;

	@Column(name = "isactive")
	private boolean isActive = false;

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

	public AssignDiscount(UUID uid, boolean isActive, Long creationDate, Long modificationDate, Long expiryDate, User user) {
		this.uid = uid;
		this.isActive = isActive;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
		this.expiryDate = expiryDate;
		this.user = user;
	}

    public DiscountPack getDiscountPack() {
        return discountPack;
    }

    public void setDiscountPack(DiscountPack discountPack) {
        this.discountPack = discountPack;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public Long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Long creationDate) {
		this.creationDate = creationDate;
	}

	public Long getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Long modificationDate) {
		this.modificationDate = modificationDate;
	}

	public Long getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Long expiryDate) {
		this.expiryDate = expiryDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
