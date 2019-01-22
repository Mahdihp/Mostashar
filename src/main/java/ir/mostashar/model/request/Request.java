package ir.mostashar.model.request;

import ir.mostashar.model.acceptRequest.AcceptRequest;
import ir.mostashar.model.adviceType.AdviceType;
import ir.mostashar.model.calls.Call;
import ir.mostashar.model.client.Client;
import ir.mostashar.model.consumptionPack.ConsumptionPack;
import ir.mostashar.model.failRequest.FailRequest;
import ir.mostashar.model.feedback.Feedback;
import ir.mostashar.model.file.File;
import lombok.Data;

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

    @Column(name = "requestnumber")
    private String requestNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "deleted")
    private boolean deleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientid", nullable = false)
    private Client client;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "request")
    private Set<Call> calls;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "request")
    private FailRequest  failRequest;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "request")
    private Set<ConsumptionPack> comments = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "request")
    private Set<Feedback> feedbacks = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fileid",nullable = true)
    private File file;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "request")
    private Set<AcceptRequest> acceptRequests = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "advicetypeid", nullable = false)
    private AdviceType advicetype;



    public Request() {
    }



}
