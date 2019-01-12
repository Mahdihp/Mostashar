package ir.mostashar.model.acceptRequest;

import ir.mostashar.model.lawyer.Lawyer;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "acceptrequests")
public class AcceptRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "acceptdate")
    private Long acceptDate;

    @Column(name = "verified")
    private Long verified;

    @Column(name = "finished")
    private Long finished;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lawyerid",nullable = false)
    private Lawyer lawyer;

    public AcceptRequest() {
    }

    public AcceptRequest(UUID uid, Long acceptDate, Long verified, Long finished) {
        this.uid = uid;
        this.acceptDate = acceptDate;
        this.verified = verified;
        this.finished = finished;
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

    public Long getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Long acceptDate) {
        this.acceptDate = acceptDate;
    }

    public Long getVerified() {
        return verified;
    }

    public void setVerified(Long verified) {
        this.verified = verified;
    }

    public Long getFinished() {
        return finished;
    }

    public void setFinished(Long finished) {
        this.finished = finished;
    }
}
