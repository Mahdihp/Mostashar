package ir.mostashar.model.role.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ir.mostashar.model.role.Role;
import ir.mostashar.model.role.dto.RoleDTO;
import ir.mostashar.model.role.logic.RoleMgr;

@RestController
@RequestMapping("/IranPopulation/api/role")
public class RoleService {

	@Autowired
	private RoleMgr roleMgr;

	@PostMapping("/items")
	public RoleDTO addRole(@RequestBody RoleDTO roleDto) {

		Role role = new Role();
		roleDto.saveTo(role);
		Role newRole = roleMgr.save(role);

		RoleDTO newRoleDto = new RoleDTO();
		newRoleDto.loadFrom(newRole);

		return newRoleDto;
	}

	@GetMapping("/items")
	public List<RoleDTO> getRoles() {
		List<RoleDTO> result = new ArrayList<RoleDTO>();
		List<Role> roles = roleMgr.findAll();
		for (Role role : roles) {
			RoleDTO roleDto = new RoleDTO();
			roleDto.loadFrom(role);
			result.add(roleDto);
		}
		return result;
	}

}
