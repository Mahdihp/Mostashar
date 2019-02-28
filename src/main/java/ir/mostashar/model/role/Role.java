package ir.mostashar.model.role;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;

import ir.mostashar.model.feature.Feature;
import ir.mostashar.model.user.User;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

@ToString
@Data
@Entity
@Table(name = "roles")
public class Role {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private UUID uid;

	@Column(name = "description")
	private String description;

	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(length = 60)
	private RoleName name;

	@Column(name = "userdefined")
	private boolean userDefined;

	@ManyToMany(mappedBy = "roles")
	private Set<User> users = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "role_feature", joinColumns = @JoinColumn(name = "roleid", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "featureid", referencedColumnName = "id"))
	private Set<Feature> features = new HashSet<>();


}
