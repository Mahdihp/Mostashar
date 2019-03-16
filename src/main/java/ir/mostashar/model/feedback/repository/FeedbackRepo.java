package ir.mostashar.model.feedback.repository;

import ir.mostashar.model.feedback.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FeedbackRepo extends JpaRepository<FeedBack, Long> {

    Optional<FeedBack> findByUid(UUID uid);

    Optional<List<FeedBack>> findByLawyerUid(UUID clientUid);

    Optional<List<FeedBack>> findByLawyerUidAndByRequestID(UUID clientUid,UUID requestUid);

    Optional<List<FeedBack>> findByRequestUid(UUID requestUid);

}
