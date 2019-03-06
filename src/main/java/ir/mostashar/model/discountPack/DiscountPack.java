package ir.mostashar.model.discountPack;

import ir.mostashar.model.assignDiscount.AssignDiscount;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
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
    private int value;

    @Column(name = "type")
    private int type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "discountpack")
    @EqualsAndHashCode.Exclude
    private Set<AssignDiscount> assignDiscounts= new HashSet<>();


    public DiscountPack() {
    }

}
