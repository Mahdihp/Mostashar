package ir.mostashar.model.pack.repository;

import ir.mostashar.model.pack.Pack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PackRepo extends JpaRepository<Pack,Long> {

    Optional<Pack> findPackByUid(UUID uuid);
    Optional<Pack> findPackByName(String uuid);

    Optional<Boolean> existsPackByName(String name);

}
