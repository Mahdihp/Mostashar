package ir.mostashar.model.question;

import ir.mostashar.model.answer.Answer;
import ir.mostashar.model.client.Client;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "typeUser")
    private int type;

    @Column(name = "creationdate")
    private Long creationDate;

    @Column(name = "edited")
    private boolean edited = false;

    @Column(name = "modificationdate")
    private Long modificationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clientId", nullable = false)
    private Client client;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private Set<Answer> answers;

    public Question() {
    }

    public Question(UUID uid, String title, String description, int type, Long creationDate, boolean edited, Long modificationDate, Client client, Set<Answer> answers) {
        this.uid = uid;
        this.title = title;
        this.description = description;
        this.type = type;
        this.creationDate = creationDate;
        this.edited = edited;
        this.modificationDate = modificationDate;
        this.client = client;
        this.answers = answers;
    }
}
