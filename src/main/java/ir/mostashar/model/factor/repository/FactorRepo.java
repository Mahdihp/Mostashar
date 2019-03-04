package ir.mostashar.model.factor.repository;

import ir.mostashar.model.factor.Factor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FactorRepo extends JpaRepository<Factor,Long> {

    Optional<Factor> findByUid(UUID uuid);

    Optional<List<Factor>> findAllByClientNameAndDeleted(String clientName,boolean isDeleted);
    Optional<List<Factor>> findAllByFactorNumberAndDeleted(String factorNumber,boolean isDeleted);
    Optional<List<Factor>> findAllByCreationDateAndDeleted(Long createDate,boolean isDeleted);
    Optional<List<Factor>> findAllByBillUidAndDeleted(UUID uuid,boolean isDeleted);


    @Query("SELECT max(fa.factorNumber) FROM Factor fa")
    Long findMaxFactorNumber();

}
