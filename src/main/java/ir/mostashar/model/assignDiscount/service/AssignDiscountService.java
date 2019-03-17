package ir.mostashar.model.assignDiscount.service;

import ir.mostashar.model.assignDiscount.AssignDiscount;
import ir.mostashar.model.assignDiscount.dto.AssignDiscountDTO;
import ir.mostashar.model.assignDiscount.dto.ListAssignDiscountDTO;
import ir.mostashar.model.assignDiscount.repository.AssignDiscountRepo;
import ir.mostashar.model.discountPack.DiscountPack;
import ir.mostashar.model.discountPack.service.DiscountPackService;
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
public class AssignDiscountService {

    @Autowired
    AssignDiscountRepo adRepo;

    @Autowired
    DiscountPackService discountPackService;

    @Autowired
    UserServiceImpl userService;

    public boolean createAssignDiscount(String userId, String codeOff) {
        Optional<DiscountPack> discountPack = discountPackService.findByCodeOff(codeOff);
        Optional<User> user = userService.findById(userId);
        if (discountPack.isPresent() && user.isPresent()) {
            AssignDiscount assignDiscount = new AssignDiscount();
            assignDiscount.setUid(UUID.randomUUID());
            assignDiscount.setDiscountpack(discountPack.get());
            assignDiscount.setUser(user.get());
            adRepo.save(assignDiscount);
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


    public Optional<AssignDiscount> findByUid(String uid) {
        Optional<AssignDiscount> assignDiscount = adRepo.findByUid(UUID.fromString(uid));
        if (assignDiscount.isPresent())
            return Optional.ofNullable(assignDiscount.get());
        else
            return Optional.empty();
    }

    public Optional<AssignDiscountDTO> findDTOByUid(String uid) {
        Optional<AssignDiscount> assignDiscount = adRepo.findByUid(UUID.fromString(uid));
        if (assignDiscount.isPresent()) {
            AssignDiscountDTO adDTO = new AssignDiscountDTO();
            adDTO.setStatus(HttpStatus.OK.value());
            adDTO.setMessage(Constants.KEY_SUCESSE);

            adDTO.setAssignDiscountId(assignDiscount.get().getUid().toString());
            adDTO.setDiscountpackId(assignDiscount.get().getDiscountpack().getUid().toString());
            adDTO.setUserId(assignDiscount.get().getUser().getUid().toString());
            return Optional.ofNullable(adDTO);
        }
        return Optional.empty();
    }

    public Optional<ListAssignDiscountDTO> findAllDTOByUid(String uid) {
        Optional<List<AssignDiscount>> list = adRepo.findAllByUserUid(UUID.fromString(uid)); // all user adminConfirmationId
        if (list.isPresent()) {
            ListAssignDiscountDTO ladDTP = new ListAssignDiscountDTO();
            ladDTP.setStatus(HttpStatus.OK.value());
            ladDTP.setMessage(Constants.KEY_SUCESSE);
            List<AssignDiscountDTO> dtoList = new ArrayList<>();
            for (AssignDiscount assignDiscount : list.get()) {
                AssignDiscountDTO adDTO = new AssignDiscountDTO();
                adDTO.setAssignDiscountId(assignDiscount.getUid().toString());
                adDTO.setUserId(assignDiscount.getUser().getUid().toString());
                adDTO.setDiscountpackId(assignDiscount.getDiscountpack().getUid().toString());

                dtoList.add(adDTO);
            }
            return Optional.ofNullable(ladDTP);
        }
        return Optional.empty();
    }


}
