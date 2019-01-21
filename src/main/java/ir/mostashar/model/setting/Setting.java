package ir.mostashar.model.setting;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ir.mostashar.model.settingType.SettingTypes;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ir.mostashar.model.user.User;

@Data
@Entity
@Table(name = "settings")
public class Setting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private UUID uid;

	@Column(name = "description")
	private String description;

    @Column(name = "userdefined")
    private boolean userDefined =false;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnore
    private User user;

	@ManyToOne
    @JoinColumn(name = "settingtypeid",nullable = false)
	private SettingTypes settingType;

	public Setting() {
	}
}
