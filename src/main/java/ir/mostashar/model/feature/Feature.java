package ir.mostashar.model.feature;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import ir.mostashar.model.role.Role;
import lombok.Data;

@Data
@Entity
@Table(name = "features")
public class Feature {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private UUID uid;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "groupkey")
	private String groupKey;

	@ManyToMany(mappedBy = "features")
    private Set<Role> roles = new HashSet<>();

	public Feature() {
	}


	public Feature(UUID uid, String name, String description, String groupKey, Set<Role> roles) {
		this.uid = uid;
		this.name = name;
		this.description = description;
		this.groupKey = groupKey;
		this.roles = roles;
	}
}
