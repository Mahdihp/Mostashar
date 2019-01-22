package ir.mostashar.model.failRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.request.Request;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "failrequests")
public class FailRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "creationdate")
    private Long creationDate;

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private Request request;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "failrequest")
    private Set<Lawyer> lawyers = new HashSet<>();

    public FailRequest() {
    }


}
