package ir.mostashar.model.feedback;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.request.Request;
import lombok.Data;
import org.aspectj.apache.bcel.generic.InstructionConstants;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "feedbacks")
public class Feedback {

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
    private boolean read;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientid", nullable = false)
    private Client client;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "requestid", nullable = false)
    private Request request;

    public Feedback() {
    }

    public Feedback(UUID uid, Long creationDate, String description, boolean read, Client client, Request request) {
        this.uid = uid;
        this.creationDate = creationDate;
        this.description = description;
        this.read = read;
        this.client = client;
        this.request = request;
    }
}