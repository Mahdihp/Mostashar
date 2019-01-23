package ir.mostashar.model.device.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ir.mostashar.model.device.Device;
import ir.mostashar.model.device.dto.DeviceDTO;
import ir.mostashar.model.device.logic.DeviceMgr;

@RestController
@RequestMapping("/Mostashar/api/device")
public class DeviceService {

	@Autowired
	private DeviceMgr deviceMgr;

	@PostMapping("/items")
	public DeviceDTO addUser(@RequestBody DeviceDTO deviceDTO) {

		Device device = new Device();
		Device newDevice = deviceMgr.save(device);

//		UserDTO newUserDto = new UserDTO();
//		newUserDto.saveTo(newUser);
//		return newUserDto;
		
		return null;
	}
}
