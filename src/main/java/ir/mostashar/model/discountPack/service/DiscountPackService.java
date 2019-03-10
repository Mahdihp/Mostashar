package ir.mostashar.model.discountPack.service;

import ir.mostashar.model.discountPack.DiscountPack;
import ir.mostashar.model.discountPack.DiscountPackType;
import ir.mostashar.model.discountPack.dto.DiscountPackDTO;
import ir.mostashar.model.discountPack.dto.DiscountPackForm;
import ir.mostashar.model.discountPack.dto.ListDiscountPackDTO;
import ir.mostashar.model.discountPack.repository.DiscountPackRepo;
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
public class DiscountPackService {

    @Autowired
    DiscountPackRepo discountPackRepo;

    public void create(DiscountPackForm dpForm) {
        Optional<Boolean> exists = discountPackRepo.existsByTitle(dpForm.getTitle());
        if (!exists.isPresent() && exists.get()) {
            DiscountPack discountPack = new DiscountPack();
            discountPack.setUid(UUID.randomUUID());
            discountPack.setTitle(dpForm.getTitle());
            discountPack.setCodeOff(DataUtil.generateOffPackCode(8));
            discountPack.setActive(dpForm.getActive());
            discountPack.setCreationDate(System.currentTimeMillis());
            discountPack.setExpiryDate(dpForm.getExpiryDate());
            discountPack.setValue(dpForm.getValue());
            discountPack.setType(DiscountPackType.valueOf(dpForm.getType()));
            discountPackRepo.save(discountPack);
        }
    }

    public void update(DiscountPackForm dpForm) {
        Optional<DiscountPack> discountPack = discountPackRepo.findByUid(UUID.fromString(dpForm.getDiscountPackId()));
        if (discountPack.isPresent()) {
            discountPack.get().setTitle(dpForm.getTitle());
            discountPack.get().setCodeOff(DataUtil.generateOffPackCode(8));
            discountPack.get().setActive(dpForm.getActive());
            discountPack.get().setCreationDate(System.currentTimeMillis());
            discountPack.get().setExpiryDate(dpForm.getExpiryDate());
            discountPack.get().setValue(dpForm.getValue());
            discountPack.get().setType(DiscountPackType.valueOf(dpForm.getType()));
            discountPackRepo.save(discountPack.get());
        }
    }

    public Optional<DiscountPack> findByUid(String uid) {
        Optional<DiscountPack> discountPack = discountPackRepo.findByUid(UUID.fromString(uid));
        if (discountPack.isPresent())
            return Optional.ofNullable(discountPack.get());
        else
            return Optional.empty();
    }


    public Optional<DiscountPackDTO> findDTOByUid(String uid) {
        Optional<DiscountPack> discountPack = discountPackRepo.findByUid(UUID.fromString(uid));
        if (discountPack.isPresent()) {
            DiscountPackDTO discountPackDTO = new DiscountPackDTO();
            discountPackDTO.setTitle(discountPack.get().getTitle());
            discountPackDTO.setCodeOff(discountPack.get().getCodeOff());
            discountPackDTO.setActive(discountPack.get().getActive());
            discountPackDTO.setCreationDate(discountPack.get().getCreationDate());
            discountPackDTO.setExpiryDate(discountPack.get().getExpiryDate());
            discountPackDTO.setValue(discountPack.get().getValue());
            discountPackDTO.setType(discountPack.get().getType().name());
            return Optional.ofNullable(discountPackDTO);
        }
        return Optional.empty();
    }

    public Optional<ListDiscountPackDTO> findAllDTO(int queryType, String title_codeoff) {
        Optional<List<DiscountPack>> list = Optional.empty();
        switch (queryType) {
            case 1:
                list = discountPackRepo.findAllByTitleLike(title_codeoff);
                break;
            case 2:
                list = discountPackRepo.findAllByCodeOff(title_codeoff);
                break;
        }
        if (list.isPresent()) {
            ListDiscountPackDTO ldpDTP = new ListDiscountPackDTO();
            ldpDTP.setStatus(HttpStatus.OK.value());
            ldpDTP.setMessage(Constants.KEY_SUCESSE);
            List<DiscountPackDTO> dtoList = new ArrayList<>();
            for (DiscountPack discountPack : list.get()) {
                DiscountPackDTO discountPackDTO = new DiscountPackDTO();
                discountPackDTO.setTitle(discountPack.getTitle());
                discountPackDTO.setCodeOff(discountPack.getCodeOff());
                discountPackDTO.setActive(discountPack.getActive());
                discountPackDTO.setCreationDate(discountPack.getCreationDate());
                discountPackDTO.setExpiryDate(discountPack.getExpiryDate());
                discountPackDTO.setValue(discountPack.getValue());
                discountPackDTO.setType(discountPack.getType().name());
                dtoList.add(discountPackDTO);
            }
            ldpDTP.setData(dtoList);
            return Optional.ofNullable(ldpDTP);
        }
        return Optional.empty();
    }


    public boolean activeDiscount(String assigndiscountuid, boolean isActive) {
        return false;
    }
}
