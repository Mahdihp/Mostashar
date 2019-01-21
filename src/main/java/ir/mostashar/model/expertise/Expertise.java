package ir.mostashar.model.expertise;

import ir.mostashar.model.lawyer.Lawyer;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "expertises")
public class Expertise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "expertises")
    private Set<Lawyer> lawyer =new HashSet<>();

    public Expertise() {
    }

    public Expertise(UUID uid, String name, String description, Set<Lawyer> lawyer) {
        this.uid = uid;
        this.name = name;
        this.description = description;
        this.lawyer = lawyer;
    }
}
