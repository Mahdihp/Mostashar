package ir.mostashar.model.request;

import ir.mostashar.model.acceptRequest.AcceptRequest;
import ir.mostashar.model.adviceType.AdviceType;
import ir.mostashar.model.call.Call;
import ir.mostashar.model.client.Client;
import ir.mostashar.model.consumptionPack.ConsumptionPack;
import ir.mostashar.model.failRequest.FailRequest;
import ir.mostashar.model.feedback.FeedBack;
import ir.mostashar.model.file.File;
import ir.mostashar.model.notification.Notification;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "requestnumber", unique = true, nullable = false)
    private Long requestNumber;

    @Column(name = "deleted")
    private boolean deleted = false;

    @Column(name = "creationdate", updatable = false)
    @CreatedDate
    private Long creationDate;

    @Column(name = "requeststatus")
    private RequestStatus requestStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientid", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Client client;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "request")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Call> calls;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "request")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private FailRequest failRequest;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "request")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<ConsumptionPack> comments = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "request")
    @EqualsAndHashCode.Exclude
    private FeedBack feedback;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fileid")
    private File file;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "request")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<AcceptRequest> acceptRequests = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "request")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Notification> notifications = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advicetypeid", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private AdviceType advicetype;


    public Request() {
    }


}
