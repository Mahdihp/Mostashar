package ir.mostashar.model.answer;

import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.question.Question;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "description")
    private String description;

    @Column(name = "creationdate")
    private Long creationDate;

    @Column(name = "edited")
    private boolean edited = false;

    @Column(name = "modificationdate")
    private Long modificationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionid", nullable = false)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lawyerid", nullable = false)
    private Lawyer lawyer;

    public Answer() {
    }

    public Answer(UUID uid, String description, Long creationDate, boolean edited, Long modificationDate) {
        this.uid = uid;
        this.description = description;
        this.creationDate = creationDate;
        this.edited = edited;
        this.modificationDate = modificationDate;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
