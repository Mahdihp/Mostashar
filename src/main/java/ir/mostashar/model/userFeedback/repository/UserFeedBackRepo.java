package ir.mostashar.model.userFeedback.repository;

import ir.mostashar.model.userFeedback.UserFeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserFeedBackRepo extends JpaRepository<UserFeedBack,Long> {

    Optional<UserFeedBack> findByUid(UUID uuid);
    Optional<UserFeedBack> findByTitle(String title);
    Optional<List<UserFeedBack>> findAllByRead(boolean isRead);
}
