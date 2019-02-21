package ir.mostashar.model.failRequest.repository;

import ir.mostashar.model.failRequest.FailRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FailRequestRepo extends JpaRepository<FailRequest, Long> {

    Optional<FailRequest> findByUid(UUID uuid);

    List<FailRequest> findAll();
    Optional<List<FailRequest>> findAllByLawyerUid(UUID uuid);

}
