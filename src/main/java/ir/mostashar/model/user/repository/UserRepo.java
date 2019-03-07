package ir.mostashar.model.user.repository;

import ir.mostashar.model.client.Client;
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
public interface UserRepo extends JpaRepository<User, Long> {


    Optional<User> findByUid(UUID uuid);
    Optional<User> findByUsername(String username);
    Optional<User> findByUidAndVerificationCode(UUID uid, String code);


    @Query("SELECT r.mobileNumber FROM User r")
    List<User> findAll();
}
