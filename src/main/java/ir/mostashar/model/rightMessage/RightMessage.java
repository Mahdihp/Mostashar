package ir.mostashar.model.rightMessage;


import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "rightmessages")
public class RightMessage {

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

    @Column(name = "expirydate")
    private Long expiryDate;

    @Column(name = "active")
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lawyerid", nullable = false)
    private Lawyer lawyer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    public RightMessage() {
    }

}
