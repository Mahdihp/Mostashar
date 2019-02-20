package ir.mostashar.model.packsnapshot.service;

import ir.mostashar.model.adviceType.AdviceType;
import ir.mostashar.model.adviceType.repository.AdviceTypeRepository;
import ir.mostashar.model.consumptionPack.repository.ConsumptionPackRepository;
import ir.mostashar.model.pack.Pack;
import ir.mostashar.model.pack.dto.PackForm;
import ir.mostashar.model.pack.service.PackService;
import ir.mostashar.model.packsnapshot.PackSnapshot;
import ir.mostashar.model.packsnapshot.dto.ListPackSnapshotDTO;
import ir.mostashar.model.packsnapshot.dto.PackSnapshotDTO;
import ir.mostashar.model.packsnapshot.repository.PackSnapshotRepository;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    PackService packService;

    /**
     * first find Advicetype by uid
     * two assing to setAdvicetype
     * three save new Packsnapshot
     * four retrun true
     *
     * @param packForm
     */
    public boolean createPackSnapshot(PackForm packForm) {
        UUID uuid = UUID.randomUUID();
        Optional<AdviceType> adviceType = adviceTypeRepository.findAdviceTypeByUid(UUID.fromString(packForm.getAdvicetypeUid()));
        Optional<Pack> pack = packService.findPackByUid(packForm.getPackUid());
        if (adviceType.isPresent() && pack.isPresent()) {
            PackSnapshot packsnapshot = new PackSnapshot();
            packsnapshot.setUid(uuid);
            packsnapshot.setPack(pack.get());
            packsnapshot.setLawyerpriceperminute(packForm.getLawyerpriceperminute());
            packsnapshot.setPackminute(packForm.getPackminute());
//            packsnapshot.setActive(); // پرسیده شود
            packsnapshot.setAdvicetype(adviceType.get());
            packsnapshotRepository.save(packsnapshot);
            return true;
        }
        return false;
    }


    public Optional<PackSnapshot> findPackSnapshotByUid(String uid) {
        return packsnapshotRepository.findPackByUid(UUID.fromString(uid));
    }


    public Optional<ListPackSnapshotDTO> findAllPackSnapshot() {
        List<PackSnapshot> packsnapshotList = packsnapshotRepository.findAll();
        List<PackSnapshotDTO> listPackDTO = new ArrayList<>();
        for (PackSnapshot packsnapshot : packsnapshotList) {
            PackSnapshotDTO packObj = new PackSnapshotDTO();
            packObj.setUid(packsnapshot.getUid().toString());

            packObj.setPackUid(packsnapshot.getPack().getUid().toString());
            packObj.setLawyerUid(packsnapshot.getLawyer().getUid().toString());
//            packObj.setTotalprice(packsnapshot.getLawyerpriceperminute() * pricePerminute);
            packObj.setIsActive(packsnapshot.isActive());

            listPackDTO.add(packObj);
        }
        if (packsnapshotList != null)
            return Optional.of(new ListPackSnapshotDTO(HttpStatus.OK.value(), Constants.KEY_SUCESSE));
        else
            return Optional.empty();
    }


}
