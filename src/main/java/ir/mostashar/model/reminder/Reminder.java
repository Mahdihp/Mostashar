package ir.mostashar.model.reminder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.mostashar.model.notification.Notification;
import ir.mostashar.model.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "reminders")
public class Reminder {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private UUID uid;

	@Column(name = "read")
	private boolean read;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnore
    private User user;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "notificationid", nullable = false)
	private Notification notification;

	public Reminder() {
	}

}
