package ir.mostashar.model.adviceType.repository;

import ir.mostashar.model.adviceType.AdviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdviceTypeRepo extends JpaRepository<AdviceType,Long> {

    Optional<AdviceType> findByUid(UUID uuid);
    Optional<AdviceType> findByName(String name);

    Optional<List<AdviceType>> findAllByParentNull();

    Optional<List<AdviceType>> findAllByParent(AdviceType parent);
}
