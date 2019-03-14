package ir.mostashar.model.feedback;

import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.request.Request;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "feedbacks")
public class FeedBack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "creationdate")
    private Long creationDate;

    @Column(name = "description")
    private String description;

    @Column(name = "read")
    private boolean read = false;

    @Column(name = "score")
    private int score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lawyerid", nullable = false)
    private Lawyer lawyer;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "requestid", nullable = false)
    private Request request;

    public FeedBack() {
    }

}