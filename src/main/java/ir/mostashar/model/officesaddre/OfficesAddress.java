package ir.mostashar.model.officesaddre;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "officesaddress")
public class OfficesAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "title")
    private String title;

    @Column(name = "address")
    private String address;

    @Column(name = "tel")
    private String tel;

    @Column(name = "description")
    private String description;

    public OfficesAddress() {
    }

    public OfficesAddress(UUID uid, String title, String address, String tel, String description) {
        this.uid = uid;
        this.title = title;
        this.address = address;
        this.tel = tel;
        this.description = description;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
