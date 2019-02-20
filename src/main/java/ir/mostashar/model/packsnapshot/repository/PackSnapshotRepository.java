package ir.mostashar.model.packsnapshot.repository;

import ir.mostashar.model.packsnapshot.PackSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PackSnapshotRepository extends JpaRepository<PackSnapshot,Long> {

    Optional<PackSnapshot> findPackByUid(UUID uuid);



}
