package ir.mostashar.model.acceptRequest;

import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.request.Request;
import ir.mostashar.model.request.RequestStatus;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "acceptrequests")
public class AcceptRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "creationdate", updatable = false)
    @CreatedDate
    private Long creationDate;

    @Column(name = "verified")
    private Long verified;

    @Column(name = "finishedtimefile")
    private Long finishedTimeFile;

    @Column(name = "acceptedbyclient")
    private boolean acceptedByClient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lawyerid",nullable = false)
    private Lawyer lawyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requestid", nullable = false)
    private Request request;

    public AcceptRequest() {
    }


}
