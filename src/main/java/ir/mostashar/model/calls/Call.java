package ir.mostashar.model.calls;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.doc.Doc;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.request.Request;
import ir.mostashar.model.wallet.Wallet;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "calls")
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "failedretriescount")
    private int failedRetriesCount;

    @Column(name = "status")
    private String status;

    @Column(name = "calltype")
    private int callType;

    @Column(name = "starttime")
    private Long startTime;

    @Column(name = "endtime")
    private Long endTime;

    @Column(name = "creationdate")
    private Long creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientid",nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lawyerid",nullable = false)
    private Lawyer lawyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requestid", nullable = false)
    private Request request;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "call")
    private Doc doc;


    public Call() {
    }

    public Call(UUID uid, int failedRetriesCount, String status, int callType, Long startTime, Long endTime, Long creationDate, Client client, Lawyer lawyer, Request request, Doc doc) {
        this.uid = uid;
        this.failedRetriesCount = failedRetriesCount;
        this.status = status;
        this.callType = callType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.creationDate = creationDate;
        this.client = client;
        this.lawyer = lawyer;
        this.request = request;
        this.doc = doc;
    }
}
