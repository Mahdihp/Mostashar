package ir.mostashar.model.request.repository;

import ir.mostashar.model.request.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RequestRepo extends JpaRepository<Request, Long> {

    Optional<Boolean> existsByRequestUid(UUID uid);
    Optional<Request> findByUidAndDeleted(UUID uuid,boolean isDeleted);

    Optional<Request> findByFileUidAndDeleted(UUID uuid, boolean isDeleted);


    Optional<Request> findByClientUidAndDeleted(UUID uuid,boolean isDeleted);

    Optional<List<Request>> findAllByClientUidAndDeleted(UUID uuid,boolean isDeleted);


    @Query("SELECT max(ch.requestNumber) FROM Request ch")
    Long findMaxRequestNumber();
}
