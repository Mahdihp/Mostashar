package ir.mostashar.model.installment.repository;

import ir.mostashar.model.installment.Installment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InstallmentRepo extends JpaRepository<Installment,Long> {


    Optional<Installment> findByUid(UUID uuid);
    Optional<List<Installment>> findByConsumptionpackUid(UUID uuid);


}
