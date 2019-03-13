package ir.mostashar.model.adminConfirmation.service;

import ir.mostashar.model.adminConfirmation.AdminConfirmation;
import ir.mostashar.model.adminConfirmation.TypeConfirmation;
import ir.mostashar.model.adminConfirmation.dto.ACDTO;
import ir.mostashar.model.adminConfirmation.dto.AdminConfirmationForm;
import ir.mostashar.model.adminConfirmation.dto.ListACDTO;
import ir.mostashar.model.adminConfirmation.repository.AdminConfirmationRepo;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.service.LawyerService;
import ir.mostashar.model.user.User;
import ir.mostashar.model.user.service.UserServiceImpl;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminConfirmationservice {


    @Autowired
    AdminConfirmationRepo acRepo;

    @Autowired
    LawyerService lawyerService;

    @Autowired
    UserServiceImpl userService;

    public boolean createAdminConfirmation(AdminConfirmationForm acForm) {
        Optional<Lawyer> lawyer = lawyerService.findByUid(acForm.getLawyerId());
        Optional<User> user = userService.findUserByUid(acForm.getUserId());

        Optional<Boolean> title = acRepo.existsByTitle(acForm.getTitle());
        if (!title.isPresent() && lawyer.isPresent() && user.isPresent()) {
            if (!title.get()) {
                AdminConfirmation ac = new AdminConfirmation();
                ac.setUid(UUID.randomUUID());
                ac.setTitle(acForm.getTitle());
                ac.setCreationDate(System.currentTimeMillis());
                ac.setDeleted(false);
                ac.setDescription(acForm.getDescription());
                switch (acForm.getTypeConfirmation()) {
                    case 0:
                        ac.setTypeConfirmation(TypeConfirmation.VERIFIED_LAWYER);
                        break;
                    case 1:
                        ac.setTypeConfirmation(TypeConfirmation.VERIFIED_ABOUT_LAWYER);
                        break;
                    case 2:
                        ac.setTypeConfirmation(TypeConfirmation.UPGRADE_RATING);
                        break;
                    case 3:
                        ac.setTypeConfirmation(TypeConfirmation.VERIFIED_COMMENTS);
                        break;
                    case 4:
                        ac.setTypeConfirmation(TypeConfirmation.VERIFIED_AVATAR_USER);
                        break;
                }

                ac.setTargetUid(acForm.getTargetUid());
                ac.setLawyer(lawyer.get());
                ac.setUser(user.get());
                ac.setVerified(false);
                ac.setCreationDate(System.currentTimeMillis());
//                ac.setVerifiedDate(null);
                acRepo.save(ac);
                return true;
            }
        }
        return false;
    }

    public boolean updateAdminConfirmation(AdminConfirmationForm acForm) {
        Optional<AdminConfirmation> ac = acRepo.findByUid(UUID.fromString(acForm.getAdminConfirmationId()));
        if (ac.isPresent()) {
            ac.get().setTitle(acForm.getTitle());
            ac.get().setCreationDate(System.currentTimeMillis());
            ac.get().setDeleted(false);
            ac.get().setDescription(acForm.getDescription());
            switch (acForm.getTypeConfirmation()) {
                case 0:
                    ac.get().setTypeConfirmation(TypeConfirmation.VERIFIED_LAWYER);
                    break;
                case 1:
                    ac.get().setTypeConfirmation(TypeConfirmation.VERIFIED_ABOUT_LAWYER);
                    break;
                case 2:
                    ac.get().setTypeConfirmation(TypeConfirmation.UPGRADE_RATING);
                    break;
                case 3:
                    ac.get().setTypeConfirmation(TypeConfirmation.VERIFIED_COMMENTS);
                    break;
                case 4:
                    ac.get().setTypeConfirmation(TypeConfirmation.VERIFIED_AVATAR_USER);
                    break;
            }
            ac.get().setTargetUid(acForm.getTargetUid());
            ac.get().setLawyer(ac.get().getLawyer());
            ac.get().setUser(ac.get().getUser());
            ac.get().setVerified(false);
            ac.get().setCreationDate(System.currentTimeMillis());
//                ac.get().setVerifiedDate(null);
            acRepo.save(ac.get());
            return true;
        }
        return false;
    }

    public boolean verifiedConfirm(String uid, boolean isVerifeid) {
        Optional<AdminConfirmation> ac = acRepo.findByUid(UUID.fromString(uid));
        if (ac.isPresent()) {
            ac.get().setVerified(isVerifeid);
            ac.get().setCreationDate(System.currentTimeMillis());
            acRepo.save(ac.get());
            return true;
        }
        return false;
    }

    public boolean deleteAC(String uid,boolean isDeleted) {
        Optional<AdminConfirmation> ac = acRepo.findByUid(UUID.fromString(uid));
        if (ac.isPresent()) {
            ac.get().setDeleted(isDeleted);
            acRepo.save(ac.get());
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


    public Optional<ACDTO> findByUid(String uid) {
        Optional<AdminConfirmation> ac = acRepo.findByUid(UUID.fromString(uid));
        if (ac.isPresent()) {
            ACDTO acdto = new ACDTO();
            acdto.setStatus(HttpStatus.OK.value());
            acdto.setMessage(Constants.KEY_SUCESSE);

            acdto.setAdminConfirmationId(ac.get().getUid().toString());
            acdto.setTitle(ac.get().getTitle());
            acdto.setCreationDate(ac.get().getCreationDate());
            acdto.setDescription(ac.get().getDescription());
            acdto.setTypeConfirmation(ac.get().getTypeConfirmation().name());
            acdto.setTargetUid(ac.get().getTargetUid());
            acdto.setLawyerId(ac.get().getLawyer().getUid().toString());
            acdto.setUserId(ac.get().getUser().getUid().toString());
            acdto.setVerified(ac.get().isVerified());
            acdto.setVerifiedDate(ac.get().getVerifiedDate());
            return Optional.ofNullable(acdto);
        }
        return Optional.empty();
    }

    public Optional<ListACDTO> findAllDTO(int querytype, String uid, boolean isVerifeid) {
        Optional<List<AdminConfirmation>> list = Optional.empty();
        switch (querytype) {
            case 1:
                list = acRepo.findAllByVerified(isVerifeid);
                break;
            case 2:
                list = acRepo.findAllByLawyerUid(UUID.fromString(uid));
                break;
            case 3:
                list = acRepo.findAllByUserUid(UUID.fromString(uid));
                break;
        }
        if (list.isPresent()) {
            ListACDTO listACDTO = new ListACDTO();
            listACDTO.setStatus(HttpStatus.OK.value());
            listACDTO.setMessage(Constants.KEY_SUCESSE);
            List<ACDTO> acdtoList = new ArrayList<>();
            for (AdminConfirmation ac : list.get()) {
                ACDTO acdto = new ACDTO();
                acdto.setAdminConfirmationId(ac.getUid().toString());
                acdto.setTitle(ac.getTitle());
                acdto.setCreationDate(ac.getCreationDate());
                acdto.setDescription(ac.getDescription());
                acdto.setTypeConfirmation(ac.getTypeConfirmation().name());
                acdto.setTargetUid(ac.getTargetUid());
                acdto.setLawyerId(ac.getLawyer().getUid().toString());
                acdto.setUserId(ac.getUser().getUid().toString());
                acdto.setVerified(ac.isVerified());
                acdto.setVerifiedDate(ac.getVerifiedDate());
                acdtoList.add(acdto);
            }
            listACDTO.setData(acdtoList);
            return Optional.ofNullable(listACDTO);
        }
        return Optional.empty();
    }
}
