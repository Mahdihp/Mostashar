package ir.mostashar.model.adminConfirmation.repository;

import ir.mostashar.model.adminConfirmation.AdminConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminConfirmationRepo extends JpaRepository<AdminConfirmation,Long> {

    Optional<Boolean> existsByTitle(String title);
    Optional<AdminConfirmation> findByUid(UUID uuid);
}
