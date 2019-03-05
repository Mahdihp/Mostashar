package ir.mostashar.model.wallet.repository;

import ir.mostashar.model.wallet.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByUid(UUID uuid);

    Optional<Wallet> findByUidAndDeleted(UUID uuid, boolean isDelete);

    Optional<Wallet> findByUidAndUserUidAndDeleted(UUID uuid, UUID userUid, boolean isDelete);

    Optional<Wallet> findByOrganizationUidAndOrganizationNameAndDeleted(UUID orgUid, String orgUsername, boolean isDelete);

    Optional<Wallet> findByUidAndUserUid(UUID uuid, UUID userUid);

    Optional<List<Wallet>> findAllByDeleted(boolean isDelete);

    Optional<Boolean> existsByBankAccountNumberOrBankAccountSheba(String bankAccountName, String bankAccountSheba);

}
