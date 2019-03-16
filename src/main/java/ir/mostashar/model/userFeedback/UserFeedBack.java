package ir.mostashar.model.userFeedback;

import ir.mostashar.model.client.Client;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "userfeedbacks")
public class UserFeedBack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "typeUser")
    private int type;

    @Column(name = "score")
    private int score;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "creationdate")
    private Long creationDate;

    @Column(name = "read")
    private boolean read;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientid", nullable = false)
    private Client client;

    public UserFeedBack() {
    }

}
