package ir.mostashar.model.bill.repository;

import ir.mostashar.model.bill.Bill;
import ir.mostashar.model.bill.BillType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BillRepo extends JpaRepository<Bill, Long> {

    Optional<Bill> findByUid(UUID uuid);

    Optional<Bill> findByValue(Long value);

    Optional<Bill> findByTransactionNumber(String transactionNumber);

    Optional<Bill> findByTrackingNumber(String trackingNumber);

    Optional<List<Bill>> findByTransactionDate(Long transactionDate);
    Optional<List<Bill>> findByWalletUid(UUID walletUid);


    Optional<List<Bill>> findByBillStatus(BillType billType);

    Optional<Boolean> existsByTransactionNumber(String transactionNumber);
}
