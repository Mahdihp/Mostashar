package ir.mostashar.model.consumptionPack.repository;

import ir.mostashar.model.consumptionPack.ConsumptionPack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConsumptionPackRepository extends JpaRepository<ConsumptionPack,Long> {

    Optional<ConsumptionPack> findByUid(UUID uuid);
    Optional<List<ConsumptionPack>> findConsumptionPacksByRequestUidAndPackUid(UUID reqUid,UUID packUid);
    Optional<List<ConsumptionPack>> findConsumptionPacksByPackUid(UUID packUid);
    Optional<List<ConsumptionPack>> findConsumptionPacksByRequestUid(UUID reqUid);
}
