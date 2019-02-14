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


    Optional<Request> findRequestByUidAndDeleted(UUID uuid,boolean isDeleted);

    Optional<List<Request>> findAllByClientUidAndDeleted(UUID uuid,boolean isDeleted);

    Optional<Request> findRequestByClientUidAndUidAndDeleted(UUID client,UUID requestId,boolean isDeleted);

    Optional<Boolean> existsRequestByFileUidAndClientUidAndDeleted(UUID fileId,UUID clientId,boolean isDeleted);

    @Query("SELECT max(ch.requestNumber) FROM Request ch")
    Long findMaxRequestNumber();
}
