package ir.mostashar.model.packsnapshot.service;

import ir.mostashar.model.adviceType.AdviceType;
import ir.mostashar.model.adviceType.repository.AdviceTypeRepository;
import ir.mostashar.model.consumptionPack.service.ConsumptionPackService;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.repository.LawyerRepository;
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
    AdviceTypeRepository adviceTypeRepository;

    @Autowired
    ConsumptionPackService consumptionPackService;

    @Autowired
    PackService packService;

    @Autowired
    LawyerRepository lawyerRepository;


    public boolean createPackSnapshot(PackSnapshotForm packForm) {
        Optional<AdviceType> adviceType = adviceTypeRepository.findAdviceTypeByUid(UUID.fromString(packForm.getAdvicetypeUid()));
        Optional<Pack> pack = packService.findPackByName(packForm.getPackname());
        Optional<Lawyer> lawyer = lawyerRepository.findByUid(UUID.fromString(packForm.getLawyerUid()));
        if (adviceType.isPresent() && pack.isPresent()) {
            PackSnapshot packsnapshot = new PackSnapshot();
            packsnapshot.setUid(UUID.randomUUID());
            packsnapshot.setPackname(pack.get().getName());
            packsnapshot.setPackdescription(pack.get().getDescription());
            packsnapshot.setPackminute(pack.get().getMinute());
            packsnapshot.setLawyerpriceperminute(lawyer.get().getPricePerMinute());
            packsnapshot.setTotalprice(lawyer.get().getPricePerMinute() * pack.get().getMinute());
            packsnapshot.setActive(true);
            packsnapshot.setAdvicetype(adviceType.get());
            packsnapshot.setLawyer(lawyer.get());
            packsnapshotRepo.save(packsnapshot);
            return true;
        }
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
                packsnapshot.setPackname(packsnapshot.getPackname());
                packsnapshot.setPackdescription(packsnapshot.getPackdescription());
                packsnapshot.setPackminute(packsnapshot.getPackminute());
                packsnapshot.setLawyerpriceperminute(packsnapshot.getLawyer().getPricePerMinute());
                packsnapshot.setTotalprice(packsnapshot.getTotalprice());
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
