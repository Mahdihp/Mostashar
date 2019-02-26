package ir.mostashar.model.userpopularity.repository;

import ir.mostashar.model.user.User;
import ir.mostashar.model.userpopularity.UserPopularity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserPopularityRepo extends JpaRepository<UserPopularity,Long> {

    Optional<UserPopularity> findByUser(User user);
    Optional<UserPopularity> findByUserPopu(User user);

    Optional<List<UserPopularity>> findAllByUser(User user);
}
