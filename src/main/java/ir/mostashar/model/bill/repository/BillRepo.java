package ir.mostashar.model.bill.repository;

import ir.mostashar.model.bill.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BillRepo extends JpaRepository<Bill,Long> {

    Optional<Bill> findByUid(UUID uuid);
}
