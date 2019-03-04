package ir.mostashar.model.device.service;

import ir.mostashar.model.device.Device;
import ir.mostashar.model.device.dto.DeviceDTO;
import ir.mostashar.model.device.dto.DeviceForm;
import ir.mostashar.model.device.dto.ListDeviceDTO;
import ir.mostashar.model.device.repository.DeviceRepo;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.xml.ws.spi.http.HttpContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeviceService {


    @Autowired
    DeviceRepo deviceRepo;

    /**
     * Cehck duplicate by User callId & Later Create Object
     *
     * @param deviceForm
     * @return
     */
    public boolean createDevice(DeviceForm deviceForm) {
        Optional<Boolean> exists = deviceRepo.existsByUserUid(UUID.fromString(deviceForm.getUseruid()));
        if (!exists.isPresent()) {
            Device device = new Device();
            device.setUid(UUID.randomUUID());
            device.setImei(deviceForm.getImei());
            device.setFireBaseRegId(deviceForm.getFireBaseRegId());
            device.setIpAddress(deviceForm.getIpAddress());

            device.setModel(deviceForm.getModel());
            deviceRepo.save(device);
            return true;
        }
        return false;
    }

    public boolean updateDevice(DeviceForm deviceForm) {
        Optional<Device> device = deviceRepo.findByUid(UUID.fromString(deviceForm.getUid()));
        if (device.isPresent()) {
            device.get().setImei(deviceForm.getImei());
            device.get().setFireBaseRegId(deviceForm.getFireBaseRegId());
            device.get().setIpAddress(deviceForm.getIpAddress());
            device.get().setModel(deviceForm.getModel());
//            device.get().setUser();
            deviceRepo.save(device.get());
            return true;
        }
        return false;
    }

    public boolean deleteDevice(String uid) {
        Optional<Device> device = deviceRepo.findByUid(UUID.fromString(uid));
        if (device.isPresent()) {
            deviceRepo.delete(device.get());
            return true;
        }
        return false;
    }

    public Optional<Device> findDeviceByUid(String uid) {
        Optional<Device> device = deviceRepo.findByUid(UUID.fromString(uid));
        if (device.isPresent())
            return Optional.ofNullable(device.get());
        else
            return Optional.empty();
    }

    public Optional<DeviceDTO> findDeviceDTOByUid(String uid) {
        Optional<Device> device = deviceRepo.findByUid(UUID.fromString(uid));
        if (device.isPresent()) {
            DeviceDTO deviceDTO = new DeviceDTO();
            deviceDTO.setStatus(HttpStatus.OK.value());
            deviceDTO.setMessage(Constants.KEY_SUCESSE);

            deviceDTO.setImei(device.get().getImei());
            deviceDTO.setFireBaseRegId(device.get().getFireBaseRegId());
            deviceDTO.setIpAddress(device.get().getIpAddress());
            deviceDTO.setModel(device.get().getModel());
            deviceDTO.setUseruid(device.get().getUser().getUid().toString());
            return Optional.ofNullable(deviceDTO);
        }
        return Optional.empty();
    }

    public Optional<ListDeviceDTO> findAllDeviceDTO() {
        List<Device> list = deviceRepo.findAll();
        if (list != null) {
            ListDeviceDTO ldDTO = new ListDeviceDTO();
            ldDTO.setStatus(HttpStatus.OK.value());
            ldDTO.setMessage(Constants.KEY_SUCESSE);
            List<DeviceDTO> dtoList = new ArrayList<>();
            for (Device device : list) {
                DeviceDTO deviceDTO = new DeviceDTO();
                deviceDTO.setImei(device.getImei());
                deviceDTO.setFireBaseRegId(device.getFireBaseRegId());
                deviceDTO.setIpAddress(device.getIpAddress());
                deviceDTO.setModel(device.getModel());
                deviceDTO.setUseruid(device.getUser().getUid().toString());
                dtoList.add(deviceDTO);
            }
            ldDTO.setData(dtoList);
        }
        return Optional.empty();
    }

}
