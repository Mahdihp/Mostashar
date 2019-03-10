package ir.mostashar.model.assignDiscount.service;

import ir.mostashar.model.assignDiscount.AssignDiscount;
import ir.mostashar.model.assignDiscount.dto.AssignDiscountDTO;
import ir.mostashar.model.assignDiscount.dto.AssignDiscountForm;
import ir.mostashar.model.assignDiscount.dto.ListAssignDiscountDTO;
import ir.mostashar.model.assignDiscount.repository.AssignDiscountRepo;
import ir.mostashar.model.discountPack.DiscountPack;
import ir.mostashar.model.discountPack.service.DiscountPackService;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.service.LawyerService;
import ir.mostashar.utils.Constants;
import ir.mostashar.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AssignDiscountService {

    @Autowired
    AssignDiscountRepo adRepo;

    @Autowired
    DiscountPackService discountPackService;

    @Autowired
    LawyerService lawyerService;

    public boolean createAssignDiscount(AssignDiscountForm adForm) {
        Optional<DiscountPack> discountPack = discountPackService.findDiscountPackByUid(adForm.getDiscountPackId());
        Optional<Lawyer> lawyer = lawyerService.findByUid(adForm.getUserId());

        if (discountPack.isPresent() && lawyer.isPresent()) {
            AssignDiscount assignDiscount = new AssignDiscount();
            assignDiscount.setUid(UUID.randomUUID());
            assignDiscount.setCreationDate(System.currentTimeMillis());
            assignDiscount.setActive(adForm.getActive());
            assignDiscount.setExpiryDate(adForm.getExpiryDate());
            assignDiscount.setDiscountpack(discountPack.get());
            assignDiscount.setUser(lawyer.get());
            assignDiscount.setCodeOff(DataUtil.generateOffPackCode(8));
            adRepo.save(assignDiscount);
            return true;
        }
        return false;
    }

    public boolean updateAssignDiscount(AssignDiscountForm adForm) {
        Optional<AssignDiscount> assignDiscount = adRepo.findByUid(UUID.fromString(adForm.getAssignDiscountId()));
        if (assignDiscount.isPresent()) {
            assignDiscount.get().setCreationDate(System.currentTimeMillis());
            assignDiscount.get().setActive(adForm.getActive());
            assignDiscount.get().setExpiryDate(adForm.getExpiryDate());
            assignDiscount.get().setCodeOff(adForm.getCode());

//            assignDiscount.get().setDiscountpack(assignDiscount.get().getDiscountpack());
//            assignDiscount.get().setUser(assignDiscount.get().getUser());
            adRepo.save(assignDiscount.get());
            return true;
        }
        return false;
    }

    public boolean activeAssignDiscount(String uid,String lawyerUid, boolean isActive) {
        Optional<AssignDiscount> assignDiscount = adRepo.findByUidAndUserUid(UUID.fromString(uid), UUID.fromString(lawyerUid));
        if (assignDiscount.isPresent()) {
            assignDiscount.get().setActive(isActive);
            adRepo.save(assignDiscount.get());
            return true;
        }
        return false;
    }

    public boolean deleteAssignDiscount(String uid) {
        Optional<AssignDiscount> assignDiscount = adRepo.findByUid(UUID.fromString(uid));
        if (assignDiscount.isPresent()) {
            adRepo.delete(assignDiscount.get());
            return true;
        }
        return false;
    }

    public Optional<AssignDiscount> findByCodeOff(String codeOff) {
        Optional<AssignDiscount> assignDiscount = adRepo.findByCodeOff(codeOff);
        if (assignDiscount.isPresent())
            return Optional.ofNullable(assignDiscount.get());
        else
            return Optional.empty();
    }

    public Optional<AssignDiscount> findByUid(String uid) {
        Optional<AssignDiscount> assignDiscount = adRepo.findByUid(UUID.fromString(uid));
        if (assignDiscount.isPresent())
            return Optional.ofNullable(assignDiscount.get());
        else
            return Optional.empty();
    }

    public Optional<AssignDiscountDTO> findAssignDiscountDTOByUid(String uid) {
        Optional<AssignDiscount> assignDiscount = adRepo.findByUid(UUID.fromString(uid));
        if (assignDiscount.isPresent()) {
            AssignDiscountDTO adDTO = new AssignDiscountDTO();
            adDTO.setStatus(HttpStatus.OK.value());
            adDTO.setMessage(Constants.KEY_SUCESSE);
            adDTO.setAssignDiscountId(assignDiscount.get().getUid().toString());
            adDTO.setCreationDate(assignDiscount.get().getCreationDate());
            adDTO.setExpiryDate(assignDiscount.get().getExpiryDate());
            adDTO.setActive(assignDiscount.get().getActive());
            adDTO.setClientId(assignDiscount.get().getUser().getUid().toString());
            adDTO.setDiscountpackId(assignDiscount.get().getDiscountpack().getUid().toString());
            adDTO.setCode(assignDiscount.get().getCodeOff());
            return Optional.ofNullable(adDTO);
        }
        return Optional.empty();
    }

    public Optional<ListAssignDiscountDTO> findAllDTOByUid(int queryType, String uid) {
        Optional<List<AssignDiscount>> list = Optional.empty();
        switch (queryType) {
            case 1:
                list = adRepo.findAllByActive(true); // all active
                break;
            case 2:
                list = adRepo.findAllByUserUidAndActive(UUID.fromString(uid), true); // all active and user adminConfirmationId
                break;
            case 3:
                list = adRepo.findAllByUserUid(UUID.fromString(uid)); // all user adminConfirmationId
                break;
        }
        if (list.isPresent()) {
            ListAssignDiscountDTO ladDTP = new ListAssignDiscountDTO();
            ladDTP.setStatus(HttpStatus.OK.value());
            ladDTP.setMessage(Constants.KEY_SUCESSE);
            List<AssignDiscountDTO> dtoList = new ArrayList<>();
            for (AssignDiscount assignDiscount : list.get()) {
                AssignDiscountDTO adDTO = new AssignDiscountDTO();
                adDTO.setAssignDiscountId(assignDiscount.getUid().toString());
                adDTO.setCreationDate(assignDiscount.getCreationDate());
                adDTO.setExpiryDate(assignDiscount.getExpiryDate());
                adDTO.setActive(assignDiscount.getActive());
                adDTO.setClientId(assignDiscount.getUser().getUid().toString());
                adDTO.setDiscountpackId(assignDiscount.getDiscountpack().getUid().toString());
                adDTO.setCode(assignDiscount.getCodeOff());

                dtoList.add(adDTO);
            }
            return Optional.ofNullable(ladDTP);
        }
        return Optional.empty();
    }


}
