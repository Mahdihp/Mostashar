package ir.mostashar.model.adviceType.service;

import ir.mostashar.model.adviceType.AdviceType;
import ir.mostashar.model.adviceType.repository.AdviceTypeRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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




}
