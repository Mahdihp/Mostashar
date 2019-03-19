package ir.mostashar.model.acceptRequest.repository;

import ir.mostashar.model.acceptRequest.AcceptRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AcceptRequestRepo extends JpaRepository<AcceptRequest, Long> {

    Optional<Boolean> removeByUid(UUID uuid);

    Optional<AcceptRequest> findByUid(UUID uuid);
    Optional<AcceptRequest> findByRequestUidAndLawyerUid(UUID requestUid,UUID lawyerUid);

    Optional<AcceptRequest> findByUidAndLawyerUid(UUID arUid, UUID lawyerUid);

    List<AcceptRequest> findAll();

    Optional<List<AcceptRequest>> findAllByLawyerUid(UUID uuid);
    Optional<List<AcceptRequest>> findAllByRequestUid(UUID requestUid);
}
