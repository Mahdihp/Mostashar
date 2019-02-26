package ir.mostashar.model.complain;

import ir.mostashar.model.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "complains")
public class Complain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "creationdate")
    private Long creationDate;

    @Column(name = "read")
    private boolean read;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    public Complain() {
    }

    public Complain(UUID uid, String title, String description, Long creationDate, boolean read, User user) {
        this.uid = uid;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.read = read;
        this.user = user;
    }
}
