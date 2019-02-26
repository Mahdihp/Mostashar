package ir.mostashar.model.accessentry;

import ir.mostashar.model.user.User;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "accessentries")
public class AccessEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true , nullable = false)
    private UUID uid;

    @Column(name = "type")
    private int type;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "creationdate")
    @CreatedDate
    private Long creationDate;

    @Column(name = "modificationdate")
    @CreatedDate
    private Long modificationDate;

    @Column(name = "expirydate")
    @CreatedDate
    private Long expiryDate;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    public AccessEntry() {
    }

    public AccessEntry(UUID uid, int type, String name, String description, Long creationDate, Long modificationDate, Long expiryDate, User user) {
        this.uid = uid;
        this.type = type;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.expiryDate = expiryDate;
        this.user = user;
    }
}
