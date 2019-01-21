package ir.mostashar.model.role;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import ir.mostashar.model.AuditModel;
import ir.mostashar.model.feature.Feature;
import ir.mostashar.model.user.User;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Role extends AuditModel {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private UUID uid;

	@Column(unique = true)
	private String name;

	@Column(name = "description")
	private String description;


	@Column(name = "userdefined")
	private boolean userDefined;

	@ManyToMany(mappedBy = "roles")
	private Set<User> users = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_feature", joinColumns = @JoinColumn(name = "roleid", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "featureid", referencedColumnName = "id"))
	private Set<Feature> features = new HashSet<>();

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
