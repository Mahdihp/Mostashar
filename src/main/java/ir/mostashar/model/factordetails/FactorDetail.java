package ir.mostashar.model.factordetails;

import ir.mostashar.model.factor.Factor;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "factordetails") // ریز موارد فاکتور
public class FactorDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "itemname", nullable = false)
    private String itemName;

    @Column(name = "countitem")
    private int countItem;

    @Column(name = "priceper")
    private long pricePer;

    @Column(name = "totalprice", nullable = false)
    private long totalPrice;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factorid", nullable = false)
    private Factor factor;

    public FactorDetail() {
    }
}
