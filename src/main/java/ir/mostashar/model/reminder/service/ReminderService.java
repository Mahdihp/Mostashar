package ir.mostashar.model.reminder.service;

import ir.mostashar.model.client.service.UserServiceImpl;
import ir.mostashar.model.notification.Notification;
import ir.mostashar.model.notification.service.NotificationService;
import ir.mostashar.model.reminder.Reminder;
import ir.mostashar.model.reminder.dto.ListReminderDTO;
import ir.mostashar.model.reminder.dto.ReminderDTO;
import ir.mostashar.model.reminder.dto.ReminderForm;
import ir.mostashar.model.reminder.repository.ReminderRepo;
import ir.mostashar.model.user.User;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReminderService {

    @Autowired
    ReminderRepo reminderRepo;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    NotificationService notificationService;

    public boolean createReminder(ReminderForm rForm) {
        Optional<User> user = userService.findUserByUid(rForm.getUserUid());
        Optional<Notification> notification = notificationService.findByUid(rForm.getNotificationUid());
        if (user.isPresent() && notification.isPresent()) {
            Reminder reminder = new Reminder();
            reminder.setUid(UUID.randomUUID());
            reminder.setRead(false);
            reminder.setUser(user.get());
            reminder.setNotification(notification.get());
            reminderRepo.save(reminder);
            return true;
        }
        return false;
    }
    public boolean saveReminder(Reminder rForm) {
        Optional<User> user = userService.findUserByUid(rForm.getUser().getUid().toString());
        Optional<Notification> notification = notificationService.findByUid(rForm.getNotification().getUid().toString());
        if (user.isPresent() && !notification.isPresent()) {
            reminderRepo.save(rForm);
            return true;
        }
        return false;
    }

    public boolean setReadReminderByNotify(String notifyUid,boolean isRead) {
        Optional<Reminder> reminder = reminderRepo.findByNotificationUid(UUID.fromString(notifyUid));
        if (reminder.isPresent()) {
            reminder.get().setRead(isRead);
            reminderRepo.save(reminder.get());
            return true;
        }
        return false;
    }


    public boolean setReadReminder(String uid) {
        Optional<Reminder> reminder = reminderRepo.findByUid(UUID.fromString(uid));
        if (reminder.isPresent()) {
            reminder.get().setRead(true);
            reminderRepo.save(reminder.get());
            return true;
        }
        return false;
    }

    public Optional<Reminder> findReminderByUid(String uid) {
        Optional<Reminder> reminder = reminderRepo.findByUid(UUID.fromString(uid));
        if (reminder.isPresent())
            return Optional.ofNullable(reminder.get());
        else
            return Optional.empty();

    }

    public Optional<ReminderDTO> findReminderDTOByUid(String uid) {
        Optional<Reminder> reminder = reminderRepo.findByUid(UUID.fromString(uid));
        if (reminder.isPresent()) {
            ReminderDTO rDTO = new ReminderDTO();
            rDTO.setStatus(HttpStatus.OK.value());
            rDTO.setMessage(Constants.KEY_SUCESSE);
            rDTO.setId(reminder.get().getUid().toString());
            rDTO.setRead(reminder.get().isRead());
            rDTO.setUserid(reminder.get().getUser().getUid().toString());
            rDTO.setNotificationid(reminder.get().getNotification().getUid().toString());
            return Optional.ofNullable(rDTO);
        }
        return Optional.empty();
    }

    public Optional<ListReminderDTO> findListReminderDTOByNotifyUid(String notifUid) {
        Optional<List<Reminder>> list = reminderRepo.findAllByNotificationUid(UUID.fromString(notifUid));
        if (list.isPresent()) {
            ListReminderDTO lrDTO = new ListReminderDTO();
            lrDTO.setStatus(HttpStatus.OK.value());
            lrDTO.setMessage(Constants.KEY_SUCESSE);
            List<ReminderDTO> dtoList = new ArrayList<>();
            for (Reminder reminder : list.get()) {
                ReminderDTO rDTO = new ReminderDTO();
                rDTO.setId(reminder.getUid().toString());
                rDTO.setRead(reminder.isRead());
                rDTO.setUserid(reminder.getUser().getUid().toString());
                rDTO.setNotificationid(reminder.getNotification().getUid().toString());
                dtoList.add(rDTO);
            }
            lrDTO.setData(dtoList);
            return Optional.ofNullable(lrDTO);
        }
        return Optional.empty();
    }

}
