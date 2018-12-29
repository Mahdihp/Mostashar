package ir.mostashar.model.lawyer;

import ir.mostashar.model.AuditModel;
<<<<<<< HEAD

import javax.persistence.*;
import java.util.UUID;


=======
import ir.mostashar.model.device.Devices;
import ir.mostashar.model.role.Role;
import ir.mostashar.model.setting.Setting;
import ir.mostashar.model.wallet.Wallet;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
>>>>>>> origin/master
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "lawyers")
public class Lawyer extends AuditModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private UUID uid;

<<<<<<< HEAD
    @Column(name = "isavailable")
    private boolean isAvailable = false;

    @Column(name = "level")
    private int level = 1;
=======
    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(name = "password")
    private String password;

    //	@NotNull
    @Column(unique = true)
    private String mobileNumber;

    //	@Size(max = 256)
//	@Column(name = "image_url", length = 256)
//	private String imageUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "lawyer_role",
            joinColumns = { @JoinColumn(name = "lawyer_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<Role> roles = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "lawyer")
    private Wallet wallet;

    @OneToMany(mappedBy = "lawyer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Setting> settings = new HashSet<Setting>();

    @OneToMany(mappedBy = "lawyer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Devices> devices = new HashSet<Devices>();
>>>>>>> origin/master
}
