package ir.mostashar.model.client.repository;

import ir.mostashar.model.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client,Long> {

    Optional<Client> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsUserByMobileNumber(long phoneNumber);
    Optional<Client> findByUid(UUID uuid);

//    @Query("SELECT count(*) FROM client")
//    Optional<Long> countAllByClient();

    @Override
    long count();
}
