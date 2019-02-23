package ir.mostashar.model.settingType;


import ir.mostashar.model.setting.Setting;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "settingtypes")
public class SettingType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID uid;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private short type;

    @OneToMany
    @JoinColumn(name = "id")
    private Set<Setting> setting;

    public SettingType() {
    }

}
