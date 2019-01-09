package ir.mostashar.model.question;

import ir.mostashar.model.answer.Answer;
import ir.mostashar.model.client.Client;
import ir.mostashar.model.user.User;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

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

    @Column(name = "type")
    private int type;

    @Column(name = "creationdate")
    private Long creationDate;

    @Column(name = "edited")
    private boolean edited = false;

    @Column(name = "modificationdate")
    private Long modificationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clientid", nullable = false)
    private Client client;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private Set<Answer> answers;

    public Question() {
    }

    public Question(UUID uid, String title, String description, int type, Long creationDate, boolean edited, Long modificationDate) {
        this.uid = uid;
        this.title = title;
        this.description = description;
        this.type = type;
        this.creationDate = creationDate;
        this.edited = edited;
        this.modificationDate = modificationDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }

    public Long getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Long modificationDate) {
        this.modificationDate = modificationDate;
    }
}
