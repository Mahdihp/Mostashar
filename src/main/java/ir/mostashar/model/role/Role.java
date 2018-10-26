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

@Entity
@Table(name = "roles")
public class Role extends AuditModel {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private UUID uid;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "roles")
	@ManyToMany(mappedBy = "roles")
	private Set<User> users = new HashSet<>();

	// @ManyToMany(fetch = FetchType.LAZY,
	// cascade = {
	// CascadeType.PERSIST,
	// CascadeType.MERGE
	// })
	// @JoinTable(name = "role_feature",
	// joinColumns = { @JoinColumn(name = "role_id") },
	// inverseJoinColumns = { @JoinColumn(name = "feature_id") })

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_feature", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "feature_id", referencedColumnName = "id"))
	private Set<Feature> features = new HashSet<>();

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}

}
