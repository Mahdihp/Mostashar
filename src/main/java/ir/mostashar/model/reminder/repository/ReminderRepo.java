package ir.mostashar.model.reminder.repository;

import ir.mostashar.model.reminder.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReminderRepo extends JpaRepository<Reminder, Long> {

    Optional<Reminder> findByUid(UUID uuid);
    Optional<Reminder> findByUserUid(UUID uuid);
    Optional<List<Reminder>> findAllByNotificationUid(UUID uuid);



}
