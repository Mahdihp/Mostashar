package ir.mostashar.model.pack.service;

import ir.mostashar.model.adviceType.AdviceType;
import ir.mostashar.model.adviceType.repository.AdviceTypeRepo;
import ir.mostashar.model.consumptionPack.dto.ConsumptionPackForm;
import ir.mostashar.model.consumptionPack.repository.ConsumptionPackRepo;
import ir.mostashar.model.pack.Pack;
import ir.mostashar.model.pack.dto.ListPackDTO;
import ir.mostashar.model.pack.dto.PackDTO;
import ir.mostashar.model.pack.dto.PackForm;
import ir.mostashar.model.pack.repository.PackRepo;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PackService {

    @Autowired
    PackRepo packRepo;

    @Autowired
    AdviceTypeRepo adviceTypeRepo;

    @Autowired
    ConsumptionPackRepo consumptionPackRepo;

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
        Optional<AdviceType> adviceType = adviceTypeRepo.findAdviceTypeByUid(UUID.fromString(packForm.getAdvicetypeUid()));
        if (adviceType.isPresent()) {
            Pack pack = new Pack();
            pack.setUid(uuid);
            pack.setMinute(packForm.getMinute());
            pack.setDescription(packForm.getDescription());
            pack.setActive(false);
            pack.setAdvicetype(adviceType.get());
            packRepo.save(pack);
            return true;
        }
        return false;
    }

    public boolean existsPack(String packName) {
        Optional<Boolean> aBoolean = packRepo.existsPackByName(packName);
        if (aBoolean.isPresent()) {
            return aBoolean.get();
        }
        return false;
    }

    public boolean deletePack(Pack pack) {
//        Optional<Pack> pack = packRepository.findPackByUid(UUID.fromString(uidPack));
        if (pack != null) {
            packRepo.delete(pack);
            return true;
        }
        return false;
    }

    public boolean updatePack(PackForm packForm) {
        Optional<Pack> pack = packRepo.findPackByUid(UUID.fromString(packForm.getUid()));
        if (pack.isPresent()) {
            Optional<AdviceType> adviceType = adviceTypeRepo.findAdviceTypeByUid(UUID.fromString(packForm.getAdvicetypeUid()));
            pack.get().setName(packForm.getUid());
            pack.get().setDescription(packForm.getDescription());
            pack.get().setActive(packForm.isActive());
            pack.get().setMinute(packForm.getMinute());
            if (adviceType.isPresent())
                pack.get().setAdvicetype(adviceType.get());

            packRepo.save(pack.get());
            return true;
        }
        return false;
    }
    public Optional<Pack> findPackByName(String packName) {
        return packRepo.findPackByName(packName);
    }
    public Optional<Pack> findPackByUid(String uid) {
        return packRepo.findPackByUid(UUID.fromString(uid));
    }

    public Optional<PackDTO> findPackDTOByUid(String uid) {
        Optional<Pack> pack = packRepo.findPackByUid(UUID.fromString(uid));
        if (pack.isPresent()) {

            PackDTO packDTO = new PackDTO();
            packDTO.setStatus(HttpStatus.OK.value());
            packDTO.setMessage(Constants.KEY_SUCESSE);

            packDTO.setUid(pack.get().getUid().toString());
            packDTO.setName(pack.get().getName());
            packDTO.setDescription(pack.get().getDescription());
            packDTO.setPriceTotal(0L);
            packDTO.setAdvicetypeUid(pack.get().getAdvicetype().getUid().toString());
            packDTO.setIsActive(pack.get().isActive());
            packDTO.setMinute(pack.get().getMinute());

            return Optional.ofNullable(packDTO);
        }
        return Optional.empty();
    }

    public Optional<ListPackDTO> findAllPacks(int pricePerminuteByLawyer) {
        List<Pack> packList = packRepo.findAll();
        List<PackDTO> listPackDTO = new ArrayList<>();
        for (Pack pack : packList) {
            PackDTO packObj = new PackDTO();
            packObj.setUid(pack.getUid().toString());
            packObj.setName(pack.getName());
            packObj.setDescription(pack.getDescription());
            packObj.setPriceTotal((long) (pack.getMinute() * pricePerminuteByLawyer));
            packObj.setIsActive(pack.isActive());

            listPackDTO.add(packObj);
        }
        if (packList != null)
            return Optional.of(new ListPackDTO(HttpStatus.OK.value(), Constants.KEY_SUCESSE, listPackDTO));
        else
            return Optional.empty();
    }

    public boolean createBuyPack(ConsumptionPackForm packForm,String lawyerUid) {
        Optional<Pack> pack = packRepo.findPackByUid(UUID.fromString(packForm.getPackId()));

       /* if (pack.isPresent()) {
            ConsumptionPack consumptionPack = new ConsumptionPack();
            consumptionPack.setUid(UUID.randomUUID());
            consumptionPack.setValue(packForm.getValue());
            consumptionPack.setPack(pack.get());
            consumptionPack.setConsumptionTime(packForm.getConsumptionTime());
            consumptionPack.setFirstInstallmentDate(packForm.getFirstInstallmentDate());
            consumptionPack.setLastInstallmentDate(packForm.getLastInstallmentDate());

        }*/
       // And create PackSnapShot Object
        return false;
    }


}
