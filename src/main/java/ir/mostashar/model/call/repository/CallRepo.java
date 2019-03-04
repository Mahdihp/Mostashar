package ir.mostashar.model.call.repository;

import ir.mostashar.model.call.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CallRepo extends JpaRepository<Call, Long> {

    Optional<Call> findByUid(UUID uuid);

    Optional<List<Call>> findAllByClientUid(UUID clientUid);

    Optional<List<Call>> findAllByLawyerUid(UUID lawyerUid);

    Optional<List<Call>> findAllByRequestUid(UUID requestUid);

}
