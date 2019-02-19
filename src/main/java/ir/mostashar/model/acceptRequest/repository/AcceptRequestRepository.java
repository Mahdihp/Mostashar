package ir.mostashar.model.acceptRequest.repository;

import ir.mostashar.model.acceptRequest.AcceptRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AcceptRequestRepository extends JpaRepository<AcceptRequest, Long> {

    Optional<Boolean> removeByUid(UUID uuid);

    Optional<AcceptRequest> findByUid(UUID uuid);

    List<AcceptRequest> findAll();

    Optional<List<AcceptRequest>> findAllByLawyerUid(UUID uuid);

    Optional<List<AcceptRequest>> findAllByRequestUid(UUID uuid);
}
