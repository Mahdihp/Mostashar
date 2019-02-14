package ir.mostashar.model.request.repository;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.request.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {


    Optional<Request> findRequestByUid(UUID uuid);

    Optional<List<Request>> findAllByClientUid(UUID client);

    Optional<Request> findRequestByClientUidAndUid(UUID client,UUID requestId);

    @Query("SELECT max(ch.requestNumber) FROM Request ch")
    Long findMaxRequestNumber();
}
