package ir.mostashar.model.accessentry;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "accessentry")
public class AccessEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private UUID uid;

    @Column(name = "type")
    private int type;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "creationdate")
    @CreatedDate
    private Date creationDate;

    @Column(name = "modificationdate")
    @CreatedDate
    private Date modificationDate;

    @Column(name = "expirydate")
    @CreatedDate
    private Date expiryDate;







}
