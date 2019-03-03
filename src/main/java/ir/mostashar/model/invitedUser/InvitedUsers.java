package ir.mostashar.model.invitedUser;

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

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ir.mostashar.model.user.User;
import org.springframework.data.annotation.CreatedDate;

@Data
@Entity
@Table(name = "invitedUsers")
public class InvitedUsers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private UUID uid;

	@Column(name = "invitedusername")
	private String invitedUsername;

	@Column(name = "textmessage")
	private String textMessage;

	@Column(name = "invitedcode")
	private String invitedCode;

	@Column(name = "invitedphonenumber")
	private String invitedPhoneNumber;

	@Column(name = "creationdate")
	@CreatedDate
	private Long  creationDate;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnore
    private User user;

	public InvitedUsers() {
	}

}
