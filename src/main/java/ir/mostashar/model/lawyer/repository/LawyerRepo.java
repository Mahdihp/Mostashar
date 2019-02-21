package ir.mostashar.model.lawyer.repository;

import ir.mostashar.model.lawyer.Lawyer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LawyerRepo extends JpaRepository<Lawyer,Long> {

    Optional<Lawyer> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsUserByMobileNumber(long phoneNumber);
    Optional<Lawyer> findByUid(UUID uuid);
}
