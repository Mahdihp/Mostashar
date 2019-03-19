package ir.mostashar.model.adviceType.service;

import ir.mostashar.model.adviceType.AdviceType;
import ir.mostashar.model.adviceType.dto.AdviceTypeDTO;
import ir.mostashar.model.adviceType.dto.ListAdviceTypeDTO;
import ir.mostashar.model.adviceType.repository.AdviceTypeRepo;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdviceTypeService {


    @Autowired
    AdviceTypeRepo adviceTypeRepo;

    public Optional<AdviceType> findAdviceTypeByUid(String uid) {
        Optional<AdviceType> adviceType = adviceTypeRepo.findByUid(UUID.fromString(uid));
        if (adviceType.isPresent())
            return Optional.ofNullable(adviceType.get());
        else
            return Optional.empty();
    }

    public Optional<AdviceType> findAdviceTypeByName(String name) {
        Optional<AdviceType> adviceType = adviceTypeRepo.findByName(name);
        if (adviceType.isPresent())
            return Optional.ofNullable(adviceType.get());
        else
            return Optional.empty();
    }


    public Optional<ListAdviceTypeDTO> findAllDTO(boolean isParent, int parentId) {

        Optional<List<AdviceType>> list = Optional.empty();
        if (isParent) {
            list = adviceTypeRepo.findAllByParentNull();
        } else {
            list = adviceTypeRepo.findAllByParentNull();
            list = adviceTypeRepo.findAllByParent(list.get().get(parentId));
        }
        if (list.isPresent()) {
            ListAdviceTypeDTO latDTO = new ListAdviceTypeDTO();
            latDTO.setStatus(HttpStatus.OK.value());
            latDTO.setMessage(Constants.KEY_SUCESSE);
            List<AdviceTypeDTO> dtoList = new ArrayList<>();
            for (AdviceType adviceType : list.get()) {
                AdviceTypeDTO adviceTypeDTO = new AdviceTypeDTO();
                adviceTypeDTO.setAdviceTypeId(adviceType.getUid().toString());
                adviceTypeDTO.setName(adviceType.getName());
                adviceTypeDTO.setDescription(adviceType.getDescription());
                dtoList.add(adviceTypeDTO);
            }
            latDTO.setData(dtoList);
            return Optional.ofNullable(latDTO);
        }
        return Optional.empty();
    }
}
