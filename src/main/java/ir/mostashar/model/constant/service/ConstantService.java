package ir.mostashar.model.constant.service;

import ir.mostashar.model.constant.Constant;
import ir.mostashar.model.constant.dto.ConstantForm;
import ir.mostashar.model.constant.repository.ConstantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConstantService {


    @Autowired
    ConstantRepo constantRepo;

    public boolean createConstant(ConstantForm cForm) {
        Optional<Boolean> exists = constantRepo.existsByKey(cForm.getKey());
        if (exists.isPresent() && !exists.get()) {
            Constant constant = new Constant();
            constant.setUid(UUID.randomUUID());
            constant.setKey(cForm.getKey());
            constant.setValue(cForm.getValue());
            constant.setType(cForm.getType());
            constant.setDescription(cForm.getDescription());
            constantRepo.save(constant);
            return true;
        }
        return false;
    }

    public boolean updateConstant(ConstantForm cForm) {
        Optional<Constant> constant = constantRepo.findByUid(UUID.fromString(cForm.getUid()));
        if (constant.isPresent()) {
            constant.get().setKey(cForm.getKey());
            constant.get().setValue(cForm.getValue());
            constant.get().setType(cForm.getType());
            constant.get().setDescription(cForm.getDescription());
            constantRepo.save(constant.get());
            return true;
        }
        return false;
    }

    public Optional<Constant> findConstantByUid(String uid) {
        Optional<Constant> constant = constantRepo.findByUid(UUID.fromString(uid));
        if (constant.isPresent()) {
            return Optional.ofNullable(constant.get());
        }
        return Optional.empty();
    }

    public Optional<Constant> findConstantByKey(String key) {
        Optional<Constant> constant = constantRepo.findByKey(key);
        if (constant.isPresent()) {
            return Optional.ofNullable(constant.get());
        }
        return Optional.empty();
    }

    public Optional<Constant> findConstantBytype(String type) {
        Optional<Constant> constant = constantRepo.findByType(type);
        if (constant.isPresent()) {
            return Optional.ofNullable(constant.get());
        }
        return Optional.empty();
    }

    public Optional<List<Constant>> findAllConstant() {
        List<Constant> list = constantRepo.findAll();
        if (list != null) {
            return Optional.ofNullable(list);
        }
        return Optional.empty();
    }
}
