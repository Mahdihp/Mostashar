package ir.mostashar.model.sharingPerspective;

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

import ir.mostashar.model.file.File;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ir.mostashar.model.user.User;
import org.springframework.data.annotation.CreatedDate;

@Data
@Entity
@Table(name = "sharingPerspectives")
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
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnore
    private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fileid", nullable = false)
	private File file;

	public SharingPerspectives() {
	}



	public SharingPerspectives(UUID uid, Long creationDate, Long modificationDate, Long expiryDate, User user, File file) {
		this.uid = uid;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
		this.expiryDate = expiryDate;
		this.user = user;
		this.file = file;
	}
}
