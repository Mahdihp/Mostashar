package ir.mostashar.model.pack;

import ir.mostashar.model.adviceType.AdviceType;
import ir.mostashar.model.consumptionPack.ConsumptionPack;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "packs")
public class Pack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "value")
    private long  value;

    @Column(name = "isactive")
    private boolean isActive = false;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "pack")
    private Set<ConsumptionPack> consumptionpacks = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advicetypeid", nullable = false)
    private AdviceType advicetype;

    public Pack() {
    }


}
