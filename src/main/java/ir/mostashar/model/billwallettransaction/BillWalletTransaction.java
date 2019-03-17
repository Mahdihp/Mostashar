package ir.mostashar.model.billwallettransaction;

import ir.mostashar.model.request.Request;
import ir.mostashar.model.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "bills")
public class BillWalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "transactionnumber", unique = true) // ایا باید یونیک باشد.
    private String transactionNumber;

    @Column(name = "trackingnumber", unique = true) // ایا باید یونیک باشد.
    private String trackingNumber;

    @Column(name = "transactiondate")
    private Long transactionDate;

    @Column(name = "bankaccountname")
    private String bankAccountName;

    @Column(name = "bankaccountnumber")
    private String bankAccountNumber;

    @Column(name = "bankaccountsheba")
    private String bankAccountSheba;

    @Column(name = "transactiontype")
    private TransactionType transactionType;

    @Column(name = "description")
    private String description;

    @Column(name = "value")
    private long value;

    @Column(name = "deleted")
    private boolean deleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requestid")
    private Request request;


}
