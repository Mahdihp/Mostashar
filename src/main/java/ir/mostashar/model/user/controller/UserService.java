package ir.mostashar.model.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ir.mostashar.model.user.User;
import ir.mostashar.model.user.dto.UserDTO;
import ir.mostashar.model.user.logic.UserMgr;

@RestController
@RequestMapping("/Mostashar/api/user")
public class UserService {

	@Autowired
	private UserMgr userMgr;

	@PostMapping("/items")
	public UserDTO addUser(@RequestBody UserDTO userDto) {

		User user = new User();
		User newUser = userMgr.save(user);

		UserDTO newUserDto = new UserDTO();
		newUserDto.saveTo(newUser);
		return newUserDto;

	}

	@GetMapping("/items")
	public List<UserDTO> getUsers() {

		List<UserDTO> result = new ArrayList<UserDTO>();
		List<User> users = userMgr.findAll();
		for (User user : users) {
			UserDTO userDto = new UserDTO();
			userDto.loadFrom(user);
			result.add(userDto);
		}

		return result;
	}
}
