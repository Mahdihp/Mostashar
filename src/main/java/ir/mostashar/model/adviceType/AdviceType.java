package ir.mostashar.model.adviceType;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "advicetypes")
public class AdviceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "parent")
    private Long parent;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="advicetypeid")
    private AdviceType adviceType;

    @OneToMany(mappedBy="adviceType")
    private Set<AdviceType> subordinates = new HashSet<>();


    public AdviceType() {
    }

    public AdviceType(UUID uid, Long parent, String name, String description, String type, AdviceType adviceType, Set<AdviceType> subordinates) {
        this.uid = uid;
        this.parent = parent;
        this.name = name;
        this.description = description;
        this.type = type;
        this.adviceType = adviceType;
        this.subordinates = subordinates;
    }
}
