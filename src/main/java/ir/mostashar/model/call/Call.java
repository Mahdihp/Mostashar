package ir.mostashar.model.call;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.doc.Doc;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.request.Request;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "call")
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "failedretriescount") // تعداد تلاش برای تماس تماس ها جمع میشه و همه را یهو میفرسته سمت سرور
    private int failedRetriesCount;

//    @Column(name = "callstatus") //
//    private String callStatus;

    @Column(name = "calltype") // voip or call center
    private int callType;

    @Column(name = "starttime")
    private Long startTime;

    @Column(name = "endtime")
    private Long endTime;

    @Column(name = "creationdate")
    private Long creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientId",nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lawyerid",nullable = false)
    private Lawyer lawyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requestid", nullable = false)
    private Request request;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "call")
    private Set<Doc> docs = new HashSet<>();


    public Call() {
    }

}
