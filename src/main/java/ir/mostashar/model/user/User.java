package ir.mostashar.model.user;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ir.mostashar.model.AuditModel;
import ir.mostashar.model.AssignDiscount.AssignDiscounts;
import ir.mostashar.model.InvitedUser.InvitedUsers;
import ir.mostashar.model.device.Device;
import ir.mostashar.model.reminder.Reminders;
import ir.mostashar.model.role.Role;
import ir.mostashar.model.setting.Setting;
import ir.mostashar.model.sharingPerspective.SharingPerspectives;
import ir.mostashar.model.wallet.Wallet;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "users")
public class User extends AuditModel {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private UUID uid;
	
	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "fathername")
	private String fatherName;

	@Column(unique = true)
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "nationalid")
	private String nationalId;

	@Column(name = "birthdate")
	private Long birthDate;

	@Column(name = "isonline")
	private boolean isOnline = false;

	@Column(name = "score")
	private int score;

	@Column(name = "avatarhashcode")
	private String avatarHashCode;

	@Column(name = "isactive")
	private boolean isActive = false;

	@Column(unique = true)
	private String mobileNumber;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<Role> roles = new HashSet<>();
	
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "user")
    private Wallet wallet;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Setting> settings = new HashSet<Setting>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<InvitedUsers> invitedUsers = new HashSet<InvitedUsers>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AssignDiscounts> assignDiscounts = new HashSet<AssignDiscounts>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Device> devices = new HashSet<Device>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Reminders> reminders = new HashSet<Reminders>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SharingPerspectives> sharingPerspectives = new HashSet<SharingPerspectives>();
	
	public UUID getUid() {
		return uid;
	}

	public void setUid(UUID uid) {
		this.uid = uid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobineNumber) {
		this.mobileNumber = mobineNumber;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public Set<Setting> getSettings() {
		return settings;
	}

	public void setSettings(Set<Setting> settings) {
		this.settings = settings;
	}

	public Set<InvitedUsers> getInvitedUsers() {
		return invitedUsers;
	}

	public void setInvitedUsers(Set<InvitedUsers> invitedUsers) {
		this.invitedUsers = invitedUsers;
	}

	public Set<AssignDiscounts> getAssignDiscounts() {
		return assignDiscounts;
	}

	public void setAssignDiscounts(Set<AssignDiscounts> assignDiscounts) {
		this.assignDiscounts = assignDiscounts;
	}

	public Set<Device> getDevices() {
		return devices;
	}

	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}

	public Set<Reminders> getReminders() {
		return reminders;
	}

	public void setReminders(Set<Reminders> reminders) {
		this.reminders = reminders;
	}

	public Set<SharingPerspectives> getSharingPerspectives() {
		return sharingPerspectives;
	}

	public void setSharingPerspectives(Set<SharingPerspectives> sharingPerspectives) {
		this.sharingPerspectives = sharingPerspectives;
	}
	
}
