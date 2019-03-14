package ir.mostashar.model.factordetails.repository;

import ir.mostashar.model.factordetails.FactorDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FactorDetailRepo extends JpaRepository<FactorDetail, Long> {


    Optional<FactorDetail> findByUid(UUID uid);

    Optional<FactorDetail> findByItemName(String itemName);

    Optional<FactorDetail> findByItemNameLike(String itemName);

    Optional<FactorDetail> findByTotalPrice(long totalPrice);

    Optional<List<FactorDetail>> findAllByFactorUid(UUID uid);


}
