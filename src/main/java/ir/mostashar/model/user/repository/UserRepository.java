package ir.mostashar.model.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import ir.mostashar.model.user.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
    Boolean existsUserByMobileNumber(long phoneNumber);
//    User findUserByMobileNumber(String phoneNumber);

    Optional<User> findByUid(UUID uuid);
}
