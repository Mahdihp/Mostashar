package ir.mostashar.model.feature;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;

import ir.mostashar.model.role.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

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


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
                    CascadeType.PERSIST},
            mappedBy = "features")
    @EqualsAndHashCode.Exclude
    private Set<Role> roles = new HashSet<>();

    public Feature() {
    }


}