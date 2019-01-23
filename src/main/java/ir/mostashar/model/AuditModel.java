package ir.mostashar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
public abstract class AuditModel implements Serializable {
    /**
	 * //	@NotNull

	 */
	private static final long serialVersionUID = 1L;

//	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creationdate", updatable = false)
    @CreatedDate
    private Long creationDate;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modificationdate",updatable = true)
    @LastModifiedDate
    private Long modificationDate;

//    @CreatedBy
//    @Column(name = "created_by", nullable = false, length = 50, updatable = false)
//    @JsonIgnore
//    private String createdBy;
//
//    @LastModifiedBy
//    @Column(name = "last_modified_by", length = 50)
//    @JsonIgnore
//    private String lastModifiedBy;

    // Getters and Setters (Omitted for brevity)
}