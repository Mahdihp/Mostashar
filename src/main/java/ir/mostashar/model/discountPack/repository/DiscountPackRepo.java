package ir.mostashar.model.discountPack.repository;

import ir.mostashar.model.discountPack.DiscountPack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DiscountPackRepo extends JpaRepository<DiscountPack, Long> {

    Optional<Boolean> existsByTitle(String name);

    Optional<DiscountPack> findByUid(UUID uid);

    Optional<List<DiscountPack>> findAllByTitleLike(String title);
    Optional<DiscountPack> findAllByCodeOff(String code);

}
