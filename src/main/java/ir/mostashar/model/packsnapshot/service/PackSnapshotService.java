package ir.mostashar.model.packsnapshot.service;

import ir.mostashar.model.adviceType.repository.AdviceTypeRepository;
import ir.mostashar.model.consumptionPack.repository.ConsumptionPackRepository;
import ir.mostashar.model.packsnapshot.PackSnapshot;
import ir.mostashar.model.packsnapshot.dto.ListPackDTO;
import ir.mostashar.model.packsnapshot.dto.ListPackSnapshotDTO;
import ir.mostashar.model.packsnapshot.dto.PackSnapshotDTO;
import ir.mostashar.model.packsnapshot.repository.PackSnapshotRepository;
import ir.mostashar.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PackSnapshotService {

    @Autowired
    PackSnapshotRepository packsnapshotRepository;

    @Autowired
    AdviceTypeRepository adviceTypeRepository;

    @Autowired
    ConsumptionPackRepository consumptionPackRepository;

    /**
     * first find Advicetype by uid
     * two assing to setAdvicetype
     * three save new Packsnapshot
     * four retrun true
     *
     * @param packForm
     */
    public boolean createPack(PackForm packForm) {
        UUID uuid = UUID.randomUUID();
//        Optional<AdviceType> adviceType = adviceTypeRepository.findAdviceTypeByUid(UUID.fromString(packForm.getAdviceId()));
//        if (adviceType.isPresent()) {
        Packsnapshot packsnapshot = new Packsnapshot();
        packsnapshot.setUid(uuid);
        packsnapshot.setName(packForm.getName());
        packsnapshot.setMinute(packForm.getPricePerMinute());
        packsnapshot.setDescription(packForm.getDescription());
        packsnapshot.setActive(packForm.getIsActive());
//            packsnapshot.setAdvicetype(adviceType.get());
        packRepository.save(packsnapshot);
        return true;
//        }
//        return false;
    }






    public Optional<PackSnapshot> findPackByUid(String uid) {
        return packsnapshotRepository.findPackByUid(UUID.fromString(uid));
    }


    public Optional<ListPackSnapshotDTO> findAllPacks() {
        List<PackSnapshot> packsnapshotList = packsnapshotRepository.findAll();
        List<PackSnapshotDTO> listPackDTO = new ArrayList<>();
        for (PackSnapshot packsnapshot : packsnapshotList) {
            PackSnapshotDTO packObj = new PackSnapshotDTO();
            packObj.set(packsnapshot.getUid().toString());
            packObj.setName(packsnapshot.getName());
            packObj.setDescription(packsnapshot.getDescription());
            packObj.setMinute(packsnapshot.getMinute() * pricePerminute);
            packObj.setIsActive(packsnapshot.isActive());

            listPackDTO.add(packObj);
        }
        if (packsnapshotList != null)
            return Optional.of(new ListPackDTO("200", Constants.KEY_SUCESSE, listPackDTO));
        else
            return Optional.empty();
    }





}
