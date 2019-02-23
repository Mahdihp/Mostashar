package ir.mostashar.model.device.repository;

import ir.mostashar.model.device.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeviceRepo extends JpaRepository<Device, Long> {

    Optional<Boolean> existsByUserUid(UUID uuid);
    Optional<Device> findByUid(UUID uuid);


}
