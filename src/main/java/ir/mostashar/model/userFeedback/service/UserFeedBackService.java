package ir.mostashar.model.userFeedback.service;

import ir.mostashar.model.userFeedback.UserFeedBack;
import ir.mostashar.model.userFeedback.dto.UserFeedBackForm;
import ir.mostashar.model.userFeedback.repository.UserFeedBackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserFeedBackService {


    @Autowired
    UserFeedBackRepo ufbRepo;


    public boolean createUserFeedBack(UserFeedBackForm ufbForm) {
        UserFeedBack userFeedBack = new UserFeedBack();
        userFeedBack.setUid(UUID.randomUUID());
        userFeedBack.setTitle(ufbForm.getTitle());
        userFeedBack.setType(ufbForm.getType());
        userFeedBack.setDescription(ufbForm.getDescription());
        userFeedBack.setCreationDate(System.currentTimeMillis());
        userFeedBack.setRead(false);
        ufbRepo.save(userFeedBack);
        return true;
    }

    public boolean updateUserFeedBack(UserFeedBackForm ufbForm) {
        Optional<UserFeedBack> userFeedBack = ufbRepo.findByUid(UUID.fromString(ufbForm.getUid()));
        if (userFeedBack.isPresent()) {
            userFeedBack.get().setTitle(ufbForm.getTitle());
            userFeedBack.get().setType(ufbForm.getType());
            userFeedBack.get().setDescription(ufbForm.getDescription());
            userFeedBack.get().setCreationDate(System.currentTimeMillis());
            userFeedBack.get().setRead(false);
            ufbRepo.save(userFeedBack.get());
            return true;
        }
        return false;
    }
    public boolean updateReadUserFeedBack(String uid,boolean isRead){
        Optional<UserFeedBack> userFeedBack = ufbRepo.findByUid(UUID.fromString(uid));
        if (userFeedBack.isPresent()) {
            userFeedBack.get().setRead(isRead);
            ufbRepo.save(userFeedBack.get());
            return true;
        }
        return false;
    }
    public Optional<UserFeedBack> findUserFeedBackByUid(String uid) {
        Optional<UserFeedBack> userFeedBack = ufbRepo.findByUid(UUID.fromString(uid));
        if (userFeedBack.isPresent()) {
            return Optional.ofNullable(userFeedBack.get());
        }
        return Optional.empty();
    }

    public Optional<List<UserFeedBack>> findAllUserFeedBack() {
        List<UserFeedBack> list = ufbRepo.findAll();
        if (list != null) {
            return Optional.ofNullable(list);
        }
        return Optional.empty();
    }

}