package ir.mostashar.model.sharingPerspective;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.mostashar.model.file.File;
import ir.mostashar.model.user.User;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "sharingperspectives")
public class SharingPerspectives {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private UUID uid;

	@Column(name = "creationdate")
	@CreatedDate
	private Long creationDate;

	@Column(name = "modificationdate")
	@CreatedDate
	private Long modificationDate;

	@Column(name = "expirydate")
	@CreatedDate
	private Long expiryDate;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    @JsonIgnore
    private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fileid", nullable = false)
	private File file;

	public SharingPerspectives() {
	}

}
