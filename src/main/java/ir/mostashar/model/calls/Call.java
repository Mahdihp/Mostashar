package ir.mostashar.model.calls;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.doc.Doc;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.request.Request;
import ir.mostashar.model.wallet.Wallet;

import javax.persistence.*;
import java.util.UUID;

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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public int getFailedRetriesCount() {
        return failedRetriesCount;
    }

    public void setFailedRetriesCount(int failedRetriesCount) {
        this.failedRetriesCount = failedRetriesCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCallType() {
        return callType;
    }

    public void setCallType(int callType) {
        this.callType = callType;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }
}
