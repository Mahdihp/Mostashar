package ir.mostashar.model.pack;

import ir.mostashar.model.adviceType.AdviceType;
import ir.mostashar.model.consumptionPack.ConsumptionPack;
import ir.mostashar.model.failRequest.FailRequest;
import ir.mostashar.model.packsnapshot.PackSnapshot;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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

    @Column(name = "minute")
    private int  minute;

    @Column(name = "active")
    private Boolean active = false;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "pack")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<ConsumptionPack> consumptionpacks = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advicetypeid", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private AdviceType advicetype;

    public Pack() {
    }


}
