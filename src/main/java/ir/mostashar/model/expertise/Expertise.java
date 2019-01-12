package ir.mostashar.model.expertise;

import ir.mostashar.model.lawyer.Lawyer;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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

    public Set<Lawyer> getLawyer() {
        return lawyer;
    }

    public void setLawyer(Set<Lawyer> lawyer) {
        this.lawyer = lawyer;
    }

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
}
