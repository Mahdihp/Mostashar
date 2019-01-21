package ir.mostashar.model.rightMessage;


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

    @Column(name = "isactive")
    private boolean isActive;

    public RightMessage() {
    }

}
