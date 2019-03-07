package ir.mostashar.model.client.repository;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepo extends JpaRepository<Client, Long> {

    Optional<Client> findByUid(UUID uuid);
    Optional<Client> findByMobileNumber(long  mobileNumber);
    Optional<Client> findClientByUidAndActive(UUID uuid, boolean active);
    Optional<Boolean> existsUserByMobileNumber(long phoneNumber);
    Optional<Client> findByUidAndVerificationCode(UUID uid, String code);
}
