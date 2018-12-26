package ir.mostashar.model.InvitedUser;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ir.mostashar.model.user.User;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "invitedUsers")
public class InvitedUsers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private UUID uid;

	@Column(name = "invitedusername")
	private String invitedUserName;

	@Column(name = "creationdate")
	@CreatedDate
	private Date creationDate;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

	public InvitedUsers() {
	}

	public InvitedUsers(UUID uid, String invitedUserName, Date creationDate, User user) {
		this.uid = uid;
		this.invitedUserName = invitedUserName;
		this.creationDate = creationDate;
		this.user = user;
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

	public String getInvitedUserName() {
		return invitedUserName;
	}

	public void setInvitedUserName(String invitedUserName) {
		this.invitedUserName = invitedUserName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
