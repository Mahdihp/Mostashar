package ir.mostashar.model.user.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import ir.mostashar.model.role.Role;
import ir.mostashar.model.role.dto.RoleDTO;
import ir.mostashar.model.user.User;

public class UserDTO {

	@JsonProperty
	private String firstName;

	@JsonProperty
	private String lastName;

	@JsonProperty
	private String password;

	@JsonProperty
	private String userName;

	@JsonProperty
	private String mobileNumber;
	
	@JsonProperty
	private String uid;

	@JsonProperty
	private List<RoleDTO> roles;

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
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void loadFrom(User user) {
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.userName = user.getUsername();
		this.mobileNumber = user.getMobileNumber();
//		this.uid = user.getUid().toString();

		Set<Role> roleSet = user.getRoles();
		List<RoleDTO> roles = new ArrayList<RoleDTO>();
		if (roleSet != null && !roleSet.isEmpty()) {
			for (Role role : roleSet) {
				RoleDTO roleDto = new RoleDTO();
				roleDto.loadFrom(role);
				roles.add(roleDto);
			}
		}
		this.roles = roles;
	}

	public void saveTo(User user) {

		user.setFirstName(this.firstName);
		user.setLastName(this.lastName);
		user.setUsername(this.userName);
		user.setPassword(this.password);
		user.setMobileNumber(this.mobileNumber);

		Set<Role> roleSet = new HashSet<Role>();
		if (this.roles != null && !this.roles.isEmpty()) {
			for (RoleDTO roleDto : this.roles) {
				Role role = new Role();
				roleDto.saveTo(role);
				roleSet.add(role);
			}
		}
		user.setRoles(roleSet);
	}

}
