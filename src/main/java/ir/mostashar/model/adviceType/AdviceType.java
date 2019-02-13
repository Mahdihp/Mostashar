package ir.mostashar.model.adviceType;


import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.pack.Pack;
import ir.mostashar.model.request.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "advicetype")
    private Request request;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "advicetype")
    private Set<Pack> packs= new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "advicetype")
    private Set<Lawyer> lawyers;


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
