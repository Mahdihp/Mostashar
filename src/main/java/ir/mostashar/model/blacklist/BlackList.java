package ir.mostashar.model.blacklist;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "blacklists")
public class BlackList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "creationdate")
    private Long creationDate;

    @Column(name = "sourceuid")
    private String sourceUid;

    @Column(name = "expirydate")
    private Long expiryDate;

    @Column(name = "description")
    private String description;

    public BlackList() {
    }

    public BlackList(UUID uid, Long creationDate, String sourceUid, Long expiryDate, String description) {
        this.uid = uid;
        this.creationDate = creationDate;
        this.sourceUid = sourceUid;
        this.expiryDate = expiryDate;
        this.description = description;
    }
}
