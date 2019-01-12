package ir.mostashar.model.adviceType;


import ir.mostashar.model.lawyer.Lawyer;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

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

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "advicetype")
//    private Set<Lawyer> lawyers;

    public AdviceType() {
    }

    public AdviceType(UUID uid, Long parent, String name, String description, String type) {
        this.uid = uid;
        this.parent = parent;
        this.name = name;
        this.description = description;
        this.type = type;
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

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
