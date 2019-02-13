package ir.mostashar.model.consumptionPack.repository;

import ir.mostashar.model.consumptionPack.ConsumptionPack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumptionPackRepository extends JpaRepository<ConsumptionPack,Long> {


}
