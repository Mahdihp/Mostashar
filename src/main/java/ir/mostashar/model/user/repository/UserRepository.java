package ir.mostashar.model.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ir.mostashar.model.user.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
//    Boolean existsUserByMobileNumber(long phoneNumber);
//    Boolean existsByMobileNumber(long  phoneNumber);
    @Query("from User u where u.mobileNumber=?1")
    Boolean existsUserByMobileNumber(@Param("mobileNumber")long  phoneNumber);

    Optional<User> findByUid(UUID uuid);
}
