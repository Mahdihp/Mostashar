package ir.mostashar.model.constant;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;


@Data
@Entity
@Table(name = "constants")
public class Constant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    public Constant() {
    }

    public Constant(UUID uid, String key, String value, String type, String description) {
        this.uid = uid;
        this.key = key;
        this.value = value;
        this.type = type;
        this.description = description;
    }
}
