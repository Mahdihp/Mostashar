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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private UUID uid;

	@Column(name = "read")
	private String read;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    @JsonIgnore
    private User user;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "notificationid", nullable = false)
	private Notification notification;



	public Reminder() {
	}

	public Reminder(UUID uid, String read, User user, Notification notification) {
		this.uid = uid;
		this.read = read;
		this.user = user;
		this.notification = notification;
	}
}
