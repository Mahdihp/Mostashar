package ir.mostashar.model.rightMessage.repository;

import ir.mostashar.model.rightMessage.RightMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

//import sun.plugin.util.UIUtil;

@Repository
public interface RightMessageRepo extends JpaRepository<RightMessage,Long> {

    Optional<Boolean> existsByTitle(String title);
    Optional<RightMessage> findByUid(UUID uuid);

    Optional<RightMessage> findByTitle(String title);

    Optional<RightMessage> findByLawyerUid(UUID lawyerUid);


}
