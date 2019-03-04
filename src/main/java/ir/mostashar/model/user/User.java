package ir.mostashar.model.user;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.mostashar.model.accessentry.AccessEntry;
import ir.mostashar.model.assignDiscount.AssignDiscount;
import ir.mostashar.model.complain.Complain;
import ir.mostashar.model.invitedUser.InvitedUsers;
import ir.mostashar.model.device.Device;
import ir.mostashar.model.reminder.Reminder;
import ir.mostashar.model.role.Role;
import ir.mostashar.model.setting.Setting;
import ir.mostashar.model.sharingPerspective.SharingPerspectives;
import ir.mostashar.model.userpopularity.UserPopularity;
import ir.mostashar.model.wallet.Wallet;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@EqualsAndHashCode
@ToString
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users", schema = "public")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    //    @SequenceGenerator(name = "USERS_ID_SEQ", sequenceName = "USERS_ID_SEQ", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_ID_SEQ")
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "fathername")
    private String fatherName;

    @Column(name = "username",unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "nationalid")
    private String nationalId;

    @Column(name = "birthdate")
    private Long birthDate;

    @Column(name = "online")
    private Boolean online = false;

    @Column(name = "score")
    private int score;

    @Column(name = "avatarhashcode")
    private String avatarHashcode;

    @Column(name = "active")
    private Boolean active = false;

    @Column(name = "mobilenumber", unique = true)
    private Long mobileNumber;

    @Column(name = "verificationcode")
    private String verificationCode;

    @Column(name = "creationdate", updatable = false)
    @CreatedDate
    private Long creationDate;

    @Column(name = "modificationdate", updatable = true)
    @LastModifiedDate
    private Long modificationDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "roleid")})
    @EqualsAndHashCode.Exclude
    private Set<Role> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Complain> complains;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "user")
    private Wallet wallet;

    @OneToMany(mappedBy = "user")
    private Set<Setting> settings = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<AccessEntry> accessEntries = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<InvitedUsers> invitedUsers = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<AssignDiscount> assignDiscounts;

    @OneToMany(mappedBy = "user")
    private Set<Device> devices = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Reminder> reminders = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<SharingPerspectives> sharingPerspectives = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserPopularity> userPopularities = new HashSet<>();

    public User() {
    }

    public User(UUID uid, long mobileNumber) {
        this.uid = uid;
        this.mobileNumber = mobileNumber;
    }


}