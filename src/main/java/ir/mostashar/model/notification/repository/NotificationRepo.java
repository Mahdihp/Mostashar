package ir.mostashar.model.notification.repository;

import ir.mostashar.model.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {

    Optional<Notification> findByUid(UUID uid);

    Optional<Notification> findByRequestUid(UUID uid);

    Optional<List<Notification>> findAllByCreationDate(Long creationDate);

}
