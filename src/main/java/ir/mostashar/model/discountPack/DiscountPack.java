package ir.mostashar.model.discountPack;

import ir.mostashar.model.assignDiscount.AssignDiscount;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;

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

    @Column(name = "title")
    private String title;

    @Column(name = "codeoff") // رشته 8 کاراکتری باید تولید شود.
    private String codeOff;

    @Column(name = "active")
    private Boolean active = false;

    @Column(name = "creationdate")
    @CreatedDate
    private Long creationDate;

    @Column(name = "modificationdate")
    @CreatedDate
    private Long modificationDate;

    @Column(name = "expirydate")
    @CreatedDate
    private Long expiryDate;

    @Column(name = "value")
    private int value;

    @Column(name = "type")
    private DiscountPackType type; // for lawyer or client or all(public)

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "discountpack")
    @EqualsAndHashCode.Exclude
    private Set<AssignDiscount> assignDiscounts = new HashSet<>();


    public DiscountPack() {
    }

}
