package ir.mostashar.model.activity.repository;

import ir.mostashar.model.activity.LawyerActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LawyerActivityRepo extends JpaRepository<LawyerActivity, Long> {

    Optional<LawyerActivity> findByUid(UUID uid);

    Optional<LawyerActivity> findByTitle(String title);

    Optional<List<LawyerActivity>> findAllByTitle(String title);

    Optional<List<LawyerActivity>> findAllByLawyerUid(UUID lawyerUid);

    Optional<LawyerActivity> findByLawyerUid(UUID lawyerUid);

    Optional<LawyerActivity> findByDocUid(UUID docUid);

    Optional<LawyerActivity> findByFileUid(UUID fileUid);


}
