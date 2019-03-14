package ir.mostashar.model.invitedUser;

import ir.mostashar.model.user.User;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "invitedusers")
public class InvitedUser {

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

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userid", nullable = false)
    private User user;

	public InvitedUser() {
	}

}
