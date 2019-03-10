package ir.mostashar.model.adminConfirmation;

import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "adminconfirmations")
public class AdminConfirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "targetuid")
    private String targetUid;

    @Column(name = "typeconfirmation")
    private TypeConfirmation typeConfirmation;

    @Column(name = "verified")
    private boolean verified;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "creationdate")
    @CreatedDate
    private Long creationDate;

    @Column(name = "verifieddate")
    @CreatedDate
    private Long verifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lawyerid", nullable = false)
    @EqualsAndHashCode.Exclude
    private Lawyer lawyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = false)
    @EqualsAndHashCode.Exclude
    private User user;

    public AdminConfirmation() {
    }
}