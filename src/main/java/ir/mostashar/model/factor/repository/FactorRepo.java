package ir.mostashar.model.factor.repository;

import ir.mostashar.model.factor.Factor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FactorRepo extends JpaRepository<Factor,Long> {

    Optional<Factor> findByUid(UUID uuid);

    Optional<List<Factor>> findAllByClientNameAndDeleted(String clientName,boolean isDelete);
    Optional<List<Factor>> findAllByFactorNumberAndDeleted(String factorNumber,boolean isDelete);
    Optional<List<Factor>> findAllByCreationDateAndDeleted(Long createDate,boolean isDelete);


}
