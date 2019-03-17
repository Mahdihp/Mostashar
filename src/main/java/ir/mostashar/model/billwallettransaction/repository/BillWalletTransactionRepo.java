package ir.mostashar.model.billwallettransaction.repository;

import ir.mostashar.model.billwallettransaction.BillWalletTransaction;
import ir.mostashar.model.billwallettransaction.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/17/19
 * Time: 10:00
 * https://github.com/mahdihp
 */

@Repository
public interface BillWalletTransactionRepo extends JpaRepository<BillWalletTransaction, Long> {

    Optional<BillWalletTransaction> findByUid(UUID uid);

    Optional<BillWalletTransaction> findByValue(long value);

    Optional<BillWalletTransaction> findByBankAccountSheba(String shaba);

    Optional<BillWalletTransaction> findByTransactionNumber(String transactionNumber);

    Optional<BillWalletTransaction> findByTrackingNumber(String trackingNumber);

    Optional<List<BillWalletTransaction>> findAllByUserUid(UUID userId);

    Optional<List<BillWalletTransaction>> findAllByRequestUid(UUID requestId);

    Optional<List<BillWalletTransaction>> findAllByTransactionType(TransactionType transactionType);

}
