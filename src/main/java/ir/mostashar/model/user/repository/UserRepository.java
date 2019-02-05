package ir.mostashar.model.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ir.mostashar.model.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findUserByUidAndVerificationCode(UUID uid,String code);

    Boolean existsByUsername(String username);

    Optional<Boolean> existsUserByMobileNumber(long  phoneNumber);

    Optional<User> findByUid(UUID uuid);

    @Query("SELECT r.mobileNumber FROM User r")
    List<User> findAll();
}
