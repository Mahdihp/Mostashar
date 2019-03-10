package ir.mostashar.model.constant.repository;

import ir.mostashar.model.constant.Constant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConstantRepo extends JpaRepository<Constant,Long> {

    Optional<Boolean> existsByKey(String key);
    Optional<Constant> findByUid(UUID uuid);
    Optional<Constant> findByKey(String key);
    Optional<Constant> findByType(String type);

}
