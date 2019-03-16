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
    private int value; // در صورت استفاده از این کد دعوت امتیاز اضافه میشود.

    @Column(name = "typeUser")
    private int type;

    @Column(name = "description")
    private String description;

    public Constant() {
    }

}
