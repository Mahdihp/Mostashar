package ir.mostashar.model.notification.service;


import ir.mostashar.model.notification.Notification;
import ir.mostashar.model.notification.dto.ListNotificationDTO;
import ir.mostashar.model.notification.dto.NotificationDTO;
import ir.mostashar.model.notification.dto.NotificationForm;
import ir.mostashar.model.notification.repository.NotificationRepo;
import ir.mostashar.model.request.Request;
import ir.mostashar.model.request.RequestStatus;
import ir.mostashar.model.request.service.RequestService;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.xml.ws.ServiceMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotificationService {

    @Autowired
    NotificationRepo notificationRepo;

    @Autowired
    RequestService requestService;

    public boolean createNotification(@Valid NotificationForm nForm) {
        Optional<Request> request = requestService.findByUid(nForm.getRequestId());
        Optional<Notification> notification = notificationRepo.findByRequestUid(UUID.fromString(nForm.getRequestId()));
        if (!notification.isPresent() && request.isPresent()) {
            Notification notif = new Notification();
            notif.setUid(UUID.randomUUID());
            notif.setContent(nForm.getContent());
            notif.setType(nForm.getType());
            notif.setCreationDate(System.currentTimeMillis());
            notif.setNotifParentUid(notif.getNotifParentUid());
            notif.setDeleted(false);
            notif.setRequest(request.get());
            notificationRepo.save(notif);
            return true;
        }
        return false;
    }

    public boolean saveNotification(Notification notif) {
        Optional<Request> request = requestService.findByUid(notif.getUid().toString());
        Optional<Notification> notification = notificationRepo.findByRequestUid(notif.getRequest().getUid());
        if (!notification.isPresent() && request.isPresent()) {
            notificationRepo.save(notif);
            return true;
        }
        return false;
    }


    public Optional<Notification> findByUid(String uid) {
        Optional<Notification> notification = notificationRepo.findByRequestUid(UUID.fromString(uid));
        if (notification.isPresent())
            return Optional.ofNullable(notification.get());
        else
            return Optional.empty();
    }

    public Optional<NotificationDTO> findNotificationDTOByUid(String uid) {
        Optional<Notification> notification = notificationRepo.findByRequestUid(UUID.fromString(uid));
        if (notification.isPresent()) {
            NotificationDTO nDTo = new NotificationDTO();
            nDTo.setStatus(HttpStatus.OK.value());
            nDTo.setMessage(Constants.KEY_SUCESSE);
            nDTo.setId(notification.get().getUid().toString());
            nDTo.setContent(notification.get().getContent());
            nDTo.setType(notification.get().getType());
            nDTo.setCreationDate(System.currentTimeMillis());
            nDTo.setNotifParentUid(notification.get().getNotifParentUid());
            nDTo.setDeleted(false);
            nDTo.setRequestId(notification.get().getRequest().getUid().toString());
            return Optional.ofNullable(nDTo);
        }
        return Optional.empty();
    }

    public Optional<ListNotificationDTO> findNotificationDTOByCreationDate(Long creationDate) {
        Optional<List<Notification>> list = notificationRepo.findAllByCreationDate(creationDate);
        if (list.isPresent()) {
            ListNotificationDTO lnDTO = new ListNotificationDTO();
            lnDTO.setStatus(HttpStatus.OK.value());
            lnDTO.setMessage(Constants.KEY_SUCESSE);

            List<NotificationDTO> dtoList = new ArrayList<>();
            for (Notification notif : list.get()) {

                NotificationDTO nDTo = new NotificationDTO();
                nDTo.setId(notif.getUid().toString());
                nDTo.setContent(notif.getContent());
                nDTo.setType(notif.getType());
                nDTo.setCreationDate(System.currentTimeMillis());
                nDTo.setNotifParentUid(notif.getNotifParentUid());
                nDTo.setDeleted(false);
                nDTo.setRequestId(notif.getRequest().getUid().toString());
                dtoList.add(nDTo);
            }
            lnDTO.setData(dtoList);
            return Optional.ofNullable(lnDTO);
        }
        return Optional.empty();
    }


}
