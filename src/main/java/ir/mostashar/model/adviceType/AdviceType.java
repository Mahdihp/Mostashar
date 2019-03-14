package ir.mostashar.model.adviceType;


import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.pack.Pack;
import ir.mostashar.model.request.Request;
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
    private short type;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "rootid")
    private AdviceType root;

    @OneToMany(mappedBy = "root")
    private Set<AdviceType> subordinates = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "advicetype")
    private Set<Pack> packs = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "advicetype")
    private Set<Lawyer> lawyers;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "advicetype")
    private Set<Request> requests = new HashSet<>();

    public AdviceType() {
    }

}
