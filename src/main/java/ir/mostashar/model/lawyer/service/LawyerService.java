package ir.mostashar.model.lawyer.service;

import ir.mostashar.model.expertise.dto.ExpertiseDTO;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.dto.LawyerDTO;
import ir.mostashar.model.lawyer.dto.ListLawyerDTO;
import ir.mostashar.model.lawyer.repository.LawyerRepo;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LawyerService {


    @Autowired
    LawyerRepo lawyerRepo;


    public Optional<Lawyer> findLawyerUidAndActive(String userid, boolean active) {
        Optional<Lawyer> lawyer = lawyerRepo.findByUidAndActive(UUID.fromString(userid), active);
        if (lawyer.isPresent())
            return Optional.ofNullable(lawyer.get());
        else
            return Optional.empty();
    }


    public Optional<Lawyer> findByUid(String uid) {
        Optional<Lawyer> lawyer = lawyerRepo.findByUid(UUID.fromString(uid));
        if (lawyer.isPresent())
            return Optional.ofNullable(lawyer.get());
        else
            return Optional.empty();
    }

    public Optional<LawyerDTO> findLawyerDTOByUid(short queryType, String uid_userName_Mobile) {
        Optional<Lawyer> lawyer = Optional.empty();
        switch (queryType) {
            case 1:
                lawyer = lawyerRepo.findByUid(UUID.fromString(uid_userName_Mobile));
                break;
            case 2:
                lawyer = lawyerRepo.findByUsername(uid_userName_Mobile);
                break;
            case 3:
                String mobile = uid_userName_Mobile.substring(1, uid_userName_Mobile.length());
                lawyer = lawyerRepo.findByMobileNumber(mobile);
                break;
        }
        if (lawyer.isPresent()) {
            LawyerDTO lawyerDTO = new LawyerDTO();
            lawyerDTO.setStatus(HttpStatus.OK.value());
            lawyerDTO.setMessage(Constants.KEY_SUCESSE);
            lawyerDTO.setId(lawyer.get().getUid().toString());
            lawyerDTO.setFirstName(lawyer.get().getFirstName());
            lawyerDTO.setLastName(lawyer.get().getLastName());
            lawyerDTO.setUsername(lawyer.get().getUsername());
            lawyerDTO.setPassword(lawyer.get().getPassword());
            lawyerDTO.setNationalId(lawyer.get().getNationalId());
            lawyerDTO.setBirthDate(lawyer.get().getBirthDate());
            lawyerDTO.setOnline(lawyer.get().getOnline());
            lawyerDTO.setScore(lawyer.get().getScore());
            lawyerDTO.setAvatarHashcode(lawyer.get().getAvatarHashcode());
            lawyerDTO.setActive(lawyer.get().getActive());
            lawyerDTO.setMobileNumber(lawyer.get().getMobileNumber());
//            lawyerDTO.setVerificationCode(lawyer.get().getVerificationCode());
            lawyerDTO.setCreationDate(lawyer.get().getCreationDate());
//            lawyerDTO.setModificationDate(lawyer.get().getModificationDate());
            lawyerDTO.setRoleName(lawyer.get().getRoles().toString());
            lawyerDTO.setAvailable(lawyer.get().getAvailable());
            lawyerDTO.setLevel(lawyer.get().getLevel());
            lawyerDTO.setPricePerMinute(lawyer.get().getPricePerMinute());
            lawyerDTO.setVerified(lawyer.get().getVerified());
            List<ExpertiseDTO> list = new ArrayList<>();
            lawyer.get()
                    .getExpertises()
                    .stream()
                    .forEach(ex -> list.add(new ExpertiseDTO(ex.getUid().toString(), ex.getName(), ex.getDescription())));
            lawyerDTO.setExpertiseList(list);
            lawyerDTO.setOrganizationId(lawyer.get().getNationalId());
            lawyerDTO.setAdvicetypeId(lawyer.get().getAdvicetype().getUid().toString());
            return Optional.ofNullable(lawyerDTO);
        }
        return Optional.empty();
    }

    public Optional<ListLawyerDTO> findListLawyerDTO(int queryType, String level_online_active_available_verified, boolean isBoolean) {
        Optional<List<Lawyer>> list = Optional.empty();
        switch (queryType) {
            case 1:
                list = lawyerRepo.findAllByLevel(Short.valueOf(level_online_active_available_verified));
                break;
            case 2:
                list = lawyerRepo.findAllByOnline(isBoolean);
                break;
            case 3:
                list = lawyerRepo.findAllByActive(isBoolean);
                break;
            case 4:
                list = lawyerRepo.findAllByAvailable(isBoolean);
                break;
            case 5:
                list = lawyerRepo.findAllByVerified(isBoolean);
                break;
        }
        if (list.isPresent()) {
            ListLawyerDTO llDTO = new ListLawyerDTO();
            llDTO.setStatus(HttpStatus.OK.value());
            llDTO.setMessage(Constants.KEY_SUCESSE);

            List<LawyerDTO> dtoList = new ArrayList<>();
            for (Lawyer lawyer : list.get()) {
                LawyerDTO lawyerDTO = new LawyerDTO();
                lawyerDTO.setId(lawyer.getUid().toString());
                lawyerDTO.setFirstName(lawyer.getFirstName());
                lawyerDTO.setLastName(lawyer.getLastName());
                lawyerDTO.setUsername(lawyer.getUsername());
                lawyerDTO.setPassword(lawyer.getPassword());
                lawyerDTO.setNationalId(lawyer.getNationalId());
                lawyerDTO.setBirthDate(lawyer.getBirthDate());
                lawyerDTO.setOnline(lawyer.getOnline());
                lawyerDTO.setScore(lawyer.getScore());
                lawyerDTO.setAvatarHashcode(lawyer.getAvatarHashcode());
                lawyerDTO.setActive(lawyer.getActive());
                lawyerDTO.setMobileNumber(lawyer.getMobileNumber());
//            lawyerDTO.setVerificationCode(lawyer.getVerificationCode());
                lawyerDTO.setCreationDate(lawyer.getCreationDate());
//            lawyerDTO.setModificationDate(lawyer.getModificationDate());
                lawyerDTO.setRoleName(lawyer.getRoles().toString());
                lawyerDTO.setAvailable(lawyer.getAvailable());
                lawyerDTO.setLevel(lawyer.getLevel());
                lawyerDTO.setPricePerMinute(lawyer.getPricePerMinute());
                lawyerDTO.setVerified(lawyer.getVerified());
                List<ExpertiseDTO> expertelist = new ArrayList<>();
                lawyer.getExpertises().stream()
                        .forEach(ex -> expertelist.add(new ExpertiseDTO(ex.getUid().toString(), ex.getName(), ex.getDescription())));
                lawyerDTO.setExpertiseList(expertelist);
                lawyerDTO.setOrganizationId(lawyer.getNationalId());
                lawyerDTO.setAdvicetypeId(lawyer.getAdvicetype().getUid().toString());
                dtoList.add(lawyerDTO);
            }
            llDTO.setData(dtoList);
            return Optional.ofNullable(llDTO);
        }

        return Optional.empty();
    }

    public boolean setOnline(String lawyerUid,boolean isOnline) {
        Optional<Lawyer> lawyer = lawyerRepo.findByUid(UUID.fromString(lawyerUid));
        if (lawyer.isPresent()) {
            lawyer.get().setOnline(isOnline);
            lawyerRepo.save(lawyer.get());
            return true;
        }
        return false;
    }

}
