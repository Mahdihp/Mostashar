package ir.mostashar.model.adviceType.repository;

import ir.mostashar.model.adviceType.AdviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdviceTypeRepository extends JpaRepository<AdviceType,Long> {

    Optional<AdviceType> findAdviceTypeByUid(UUID uuid);
}
