package ir.mostashar.model.lawyer.repository;

import ir.mostashar.model.lawyer.Lawyer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LawyerRepo extends JpaRepository<Lawyer,Long> {

    Optional<Lawyer> findByUsername(String username);
    Optional<Lawyer> findByMobileNumber(String mobileNumber);
    Boolean existsByUsername(String username);
    Boolean existsUserByMobileNumber(long phoneNumber);
    Optional<Lawyer> findByUid(UUID uuid);
    Optional<Lawyer> findByUidAndActive(UUID uuid, boolean isActive);

    Optional<List<Lawyer>> findAllByOnline(boolean isOnline);
    Optional<List<Lawyer>> findAllByLevel(short level);
    Optional<List<Lawyer>> findAllByActive(boolean isActive);
    Optional<List<Lawyer>> findAllByAvailable(boolean isAvailable);
    Optional<List<Lawyer>> findAllByVerified(boolean isVerified);


}
