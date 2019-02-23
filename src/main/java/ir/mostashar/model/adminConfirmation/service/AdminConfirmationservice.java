package ir.mostashar.model.adminConfirmation.service;

import ir.mostashar.model.adminConfirmation.AdminConfirmation;
import ir.mostashar.model.adminConfirmation.dto.AdminConfirmationForm;
import ir.mostashar.model.adminConfirmation.repository.AdminConfirmationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminConfirmationservice {


    @Autowired
    AdminConfirmationRepo acRepo;

    public boolean createAdminConfirmation(AdminConfirmationForm acForm) {
        Optional<Boolean> title = acRepo.existsByTitle(acForm.getTitle());
        if (title.isPresent()) {
            if (!title.get()) {
                AdminConfirmation ac = new AdminConfirmation();
                ac.setUid(UUID.randomUUID());
                ac.setTitle(acForm.getTitle());
                ac.setCreationDate(System.currentTimeMillis());
                ac.setDeleted(false);
                ac.setDescription(acForm.getDescription());
                ac.setTargetType(acForm.getTargetType());
                ac.setTargetUid(acForm.getTargetUid());
                ac.setVerified(false);
                acRepo.save(ac);
                return true;
            }
        }
        return false;
    }

    public boolean updateAdminConfirmation(AdminConfirmationForm acForm) {
        Optional<AdminConfirmation> ac = acRepo.findByUid(UUID.fromString(acForm.getUid()));
        if (ac.isPresent()) {
            ac.get().setTitle(acForm.getTitle());
            ac.get().setCreationDate(System.currentTimeMillis());
            ac.get().setDeleted(false);
            ac.get().setDescription(acForm.getDescription());
            ac.get().setTargetType(acForm.getTargetType());
            ac.get().setTargetUid(acForm.getTargetUid());
            ac.get().setVerified(false);
            acRepo.save(ac.get());
            return true;
        }
        return false;
    }

    public Optional<AdminConfirmation> findACByUid(String uid) {
        Optional<AdminConfirmation> ac = acRepo.findByUid(UUID.fromString(uid));
        if (ac.isPresent())
            return Optional.ofNullable(ac.get());
        else
            return Optional.empty();
    }

    public Optional<List<AdminConfirmation>> findAllAC() {
        List<AdminConfirmation> list = acRepo.findAll();
        if (list != null)
            return Optional.ofNullable(list);
        else
            return Optional.empty();
    }
}
