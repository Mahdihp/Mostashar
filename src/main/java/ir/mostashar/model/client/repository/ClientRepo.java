package ir.mostashar.model.client.repository;

import ir.mostashar.model.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepo extends JpaRepository<Client,Long> {

    Optional<Client> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsUserByMobileNumber(long phoneNumber);
    Optional<Client> findClientByUidAndActive(UUID uuid, boolean active);

    Optional<Client> findByUid(UUID uuid);

    List<Client> findAll();
}
