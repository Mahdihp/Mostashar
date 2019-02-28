package ir.mostashar.model.discountPack;

import ir.mostashar.model.assignDiscount.AssignDiscount;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "discountpacks")
public class DiscountPack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private long value;

    @Column(name = "type")
    private int type;

    @OneToMany
    @JoinColumn(name = "discountpack")
    private Set<AssignDiscount> assignDiscount;


    public DiscountPack() {
    }

}
