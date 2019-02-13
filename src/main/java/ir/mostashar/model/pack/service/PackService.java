package ir.mostashar.model.pack.service;

import ir.mostashar.model.adviceType.AdviceType;
import ir.mostashar.model.adviceType.repository.AdviceTypeRepository;
import ir.mostashar.model.pack.Pack;
import ir.mostashar.model.pack.dto.PackDTO;
import ir.mostashar.model.pack.dto.PackForm;
import ir.mostashar.model.pack.repository.PackRepository;
import ir.mostashar.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PackService {

    @Autowired
    PackRepository packRepository;

    @Autowired
    AdviceTypeRepository adviceTypeRepository;

    /**
     * first find Advicetype by uid
     * two assing to setAdvicetype
     * three save new Pack
     * four retrun true
     *
     * @param packForm
     */
    public boolean createPack(PackForm packForm) {
        UUID uuid = UUID.randomUUID();
        Optional<AdviceType> adviceType = adviceTypeRepository.findAdviceTypeByUid(UUID.fromString(packForm.getAdviceId()));
        if (adviceType.isPresent()) {
            Pack pack = new Pack();
            pack.setUid(uuid);
            pack.setName(packForm.getName());
            pack.setMinute(packForm.getMinute());
            pack.setDescription(packForm.getDescription());
            pack.setActive(packForm.getIsActive());
            pack.setAdvicetype(adviceType.get());
            packRepository.save(pack);
            return true;
        }
        return false;
    }

    public boolean existsPack(String packName) {
        Optional<Boolean> aBoolean = packRepository.existsPackByName(packName);
        if (aBoolean.isPresent()) {
            return aBoolean.get();
        }
        return false;
    }

    public boolean deletePack(String uidPack) {
        Optional<Pack> pack = packRepository.findPackByUid(UUID.fromString(uidPack));
        if (pack.isPresent()) {
            packRepository.delete(pack.get());
            return true;
        }
        return false;
    }

    public boolean updatePack(PackForm packForm) {
        Optional<Pack> pack = packRepository.findPackByUid(UUID.fromString(packForm.getUid()));
        if (pack.isPresent()) {
            Optional<AdviceType> adviceType = adviceTypeRepository.findAdviceTypeByUid(UUID.fromString(packForm.getAdviceId()));
            pack.get().setName(packForm.getUid());
            pack.get().setDescription(packForm.getDescription());
            pack.get().setActive(packForm.getIsActive());
            pack.get().setMinute(packForm.getMinute());
            if (adviceType.isPresent())
                pack.get().setAdvicetype(adviceType.get());

            packRepository.save(pack.get());
            return true;
        }
        return false;
    }

    public Optional<Pack> findPackByUid(String uid) {
        return packRepository.findPackByUid(UUID.fromString(uid));
    }

    public Optional<PackDTO> findPackDTOByUid(String uid) {
        Optional<Pack> pack = packRepository.findPackByUid(UUID.fromString(uid));
        if (pack.isPresent()) {
            PackForm packForm = new PackForm();
            packForm.setUid(packForm.getUid());
            packForm.setName(pack.get().getName());
            packForm.setDescription(pack.get().getDescription());
            packForm.setMinute(pack.get().getMinute());
            packForm.setAdviceId(pack.get().getAdvicetype().getUid().toString());
            packForm.setIsActive(pack.get().isActive());
            PackDTO packDTO = new PackDTO();
            packDTO.setStatus("200");
            packDTO.setMessage(Constants.KEY_SUCESSE);
            packDTO.setPackForm(packForm);
            return Optional.ofNullable(packDTO);
        }
        return Optional.empty();
    }

    public Optional<PackDTO> findAllPacks() {
        List<Pack> packList = packRepository.findAll();
        System.out.println("Log------------findAllPacks "+packList.size());
        PackDTO packDTO = new PackDTO();
        for (Pack pack : packList) {
            packDTO.setStatus("200");
            packDTO.setMessage(Constants.KEY_SUCESSE);

            packDTO.setPackId(pack.getUid().toString());
            packDTO.setName(pack.getName());
            packDTO.setDescription(pack.getDescription());
            packDTO.setMinute(pack.getMinute());
            packDTO.setIsActive(pack.isActive());
            packDTO.setAdvicetype(pack.getAdvicetype());
        }
        if (packList != null)
            return Optional.ofNullable(packDTO);
        else
           return Optional.empty();
    }


}