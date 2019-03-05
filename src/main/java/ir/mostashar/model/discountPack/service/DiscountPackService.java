package ir.mostashar.model.discountPack.service;

import ir.mostashar.model.discountPack.DiscountPack;
import ir.mostashar.model.discountPack.dto.DiscountPackDTO;
import ir.mostashar.model.discountPack.dto.DiscountPackForm;
import ir.mostashar.model.discountPack.dto.ListDiscountPackDTO;
import ir.mostashar.model.discountPack.repository.DiscountPackRepo;
import ir.mostashar.utils.Constants;
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

    public void createDiscountPack(DiscountPackForm dpForm) {
        Optional<Boolean> exists = discountPackRepo.existsByName(dpForm.getName());
        if (!exists.isPresent() && exists.get()) {
            DiscountPack discountPack = new DiscountPack();
            discountPack.setUid(UUID.randomUUID());
            discountPack.setName(dpForm.getName());
            discountPack.setValue(dpForm.getValue());
            discountPack.setType(dpForm.getType());
            discountPackRepo.save(discountPack);
        }
    }

    public Optional<DiscountPackDTO> findAllByUid(String uid) {
        Optional<DiscountPack> discountPack = discountPackRepo.findByUid(UUID.fromString(uid));
        if (discountPack.isPresent()) {
            DiscountPackDTO discountPackDTO = new DiscountPackDTO();
            discountPackDTO.setStatus(HttpStatus.OK.value());
            discountPackDTO.setMessage(Constants.KEY_SUCESSE);
            discountPackDTO.setDiscountPackId(discountPack.get().getUid().toString());
            discountPackDTO.setName(discountPack.get().getName());
            discountPackDTO.setValue(discountPack.get().getValue());
            discountPackDTO.setType(discountPack.get().getType());
            return Optional.ofNullable(discountPackDTO);
        }
        return Optional.empty();
    }

    public Optional<ListDiscountPackDTO> findAllByLikeName(String name) {
        Optional<List<DiscountPack>> list = discountPackRepo.findAllByNameLike(name);
        if (list.isPresent()) {
            ListDiscountPackDTO ldpDTP = new ListDiscountPackDTO();
            ldpDTP.setStatus(HttpStatus.OK.value());
            ldpDTP.setMessage(Constants.KEY_SUCESSE);
            List<DiscountPackDTO> dtoList = new ArrayList<>();
            for (DiscountPack discountPack : list.get()) {
                DiscountPackDTO discountPackDTO = new DiscountPackDTO();
                discountPackDTO.setDiscountPackId(discountPack.getUid().toString());
                discountPackDTO.setName(discountPack.getName());
                discountPackDTO.setValue(discountPack.getValue());
                discountPackDTO.setType(discountPack.getType());
                dtoList.add(discountPackDTO);
            }
            ldpDTP.setData(dtoList);
            return Optional.ofNullable(ldpDTP);
        }
        return Optional.empty();
    }


}
