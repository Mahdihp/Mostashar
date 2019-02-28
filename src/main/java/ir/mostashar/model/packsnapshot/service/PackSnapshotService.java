package ir.mostashar.model.packsnapshot.service;

import ir.mostashar.model.adviceType.AdviceType;
import ir.mostashar.model.adviceType.repository.AdviceTypeRepo;
import ir.mostashar.model.consumptionPack.service.ConsumptionPackService;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.repository.LawyerRepo;
import ir.mostashar.model.pack.Pack;
import ir.mostashar.model.pack.service.PackService;
import ir.mostashar.model.packsnapshot.PackSnapshot;
import ir.mostashar.model.packsnapshot.dto.ListPackSnapshotDTO;
import ir.mostashar.model.packsnapshot.dto.PackSnapshotDTO;
import ir.mostashar.model.packsnapshot.dto.PackSnapshotForm;
import ir.mostashar.model.packsnapshot.repository.PackSnapshotRepo;
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
    PackSnapshotRepo packsnapshotRepo;

    @Autowired
    AdviceTypeRepo adviceTypeRepo;

    @Autowired
    ConsumptionPackService consumptionPackService;

    @Autowired
    PackService packService;

    @Autowired
    LawyerRepo lawyerRepo;


    public boolean createPackSnapshot(PackSnapshotForm packForm) {
        Optional<AdviceType> adviceType = adviceTypeRepo.findByUid(UUID.fromString(packForm.getAdvicetypeUid()));
        Optional<Pack> pack = packService.findPackByName(packForm.getPackname());
        Optional<Lawyer> lawyer = lawyerRepo.findByUid(UUID.fromString(packForm.getLawyerUid()));
        if (adviceType.isPresent() && pack.isPresent() && lawyer.isPresent()) {
            PackSnapshot packsnapshot = new PackSnapshot();
            packsnapshot.setUid(UUID.randomUUID());
            packsnapshot.setPackName(pack.get().getName());
            packsnapshot.setPackDescription(pack.get().getDescription());
            packsnapshot.setPackMinute(pack.get().getMinute());
            packsnapshot.setLawyerPricePerMinute(lawyer.get().getPricePerMinute());
            packsnapshot.setTotalPrice(lawyer.get().getPricePerMinute() * pack.get().getMinute());
            packsnapshot.setActive(true);
            packsnapshot.setAdvicetype(adviceType.get());
            packsnapshot.setLawyer(lawyer.get());
            packsnapshotRepo.save(packsnapshot);
            return true;
        }
        return false;
    }

    public boolean savePackSnapShot(PackSnapshot packsnapshot) {
        PackSnapshot packSnapshot = packsnapshotRepo.save(packsnapshot);
        if (packSnapshot != null)
            return true;
        else
            return false;

    }

    public Optional<PackSnapshot> findPackSnapshotByUid(String uid) {
        return packsnapshotRepo.findPackByUid(UUID.fromString(uid));
    }

    public Optional<ListPackSnapshotDTO> findAllPackSnapshot() {
        List<PackSnapshot> packsnapshotList = packsnapshotRepo.findAll();
        List<PackSnapshotDTO> listPackDTO = new ArrayList<>();
        if (packsnapshotList != null) {
            for (PackSnapshot packsnapshot : packsnapshotList) {

                PackSnapshotDTO packObj = new PackSnapshotDTO();
                packsnapshot.setUid(UUID.randomUUID());
                packsnapshot.setPackName(packsnapshot.getPackName());
                packsnapshot.setPackDescription(packsnapshot.getPackDescription());
                packsnapshot.setPackMinute(packsnapshot.getPackMinute());
                packsnapshot.setLawyerPricePerMinute(packsnapshot.getLawyer().getPricePerMinute());
                packsnapshot.setTotalPrice(packsnapshot.getTotalPrice());
                packsnapshot.setActive(true);
                packsnapshot.setAdvicetype(packsnapshot.getAdvicetype());
                packsnapshot.setLawyer(packsnapshot.getLawyer());

                listPackDTO.add(packObj);
            }
            return Optional.of(new ListPackSnapshotDTO(HttpStatus.OK.value(), Constants.KEY_SUCESSE));
        }
        return Optional.empty();
    }


}
