package ir.mostashar.model.assignDiscount.repository;

import ir.mostashar.model.assignDiscount.AssignDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssignDiscountRepo extends JpaRepository<AssignDiscount,Long> {

    Optional<AssignDiscount> findByUid(UUID uid);
    Optional<AssignDiscount> findByUidAndUserUid(UUID uid,UUID lawyerUid);
    Optional<List<AssignDiscount>> findAllByActive(boolean isActive);
    Optional<List<AssignDiscount>> findAllByUserUidAndActive(UUID uid,boolean isActive);
    Optional<List<AssignDiscount>> findAllByUserUid(UUID uid);

}
