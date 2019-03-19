package ir.mostashar.model.consumptionPack.service;

import ir.mostashar.model.consumptionPack.ConsumptionPack;
import ir.mostashar.model.consumptionPack.dto.ConsumptionPackForm;
import ir.mostashar.model.consumptionPack.repository.ConsumptionPackRepo;
import ir.mostashar.model.pack.Pack;
import ir.mostashar.model.pack.service.PackService;
import ir.mostashar.model.request.Request;
import ir.mostashar.model.request.repository.RequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConsumptionPackService {

    @Autowired
    ConsumptionPackRepo cpRepository;

    @Autowired
    PackService packService;

    @Autowired
    RequestRepo requestRepo;

    /**
     * Find pack & request if this two object is present
     * & createByReading & save ConsumptionPack object
     *
     * @param cpForm
     * @return false & true
     */
    public boolean createConsumptionPack(ConsumptionPackForm cpForm) {
        Optional<Pack> pack = packService.findPackByUid(cpForm.getPackId());
        Optional<Request> request = requestRepo.findByUidAndDeleted(UUID.fromString(cpForm.getRequestId()), false);
        if (pack.isPresent() && request.isPresent()) {
            ConsumptionPack cp = new ConsumptionPack();
            cp.setUid(UUID.randomUUID());
            cp.setConsumptionTime(cpForm.getConsumptionTime());
            cp.setBaseTime(cpForm.getBaseTime());
            cp.setType(cpForm.getType());
            cp.setFirstInstallmentDate(cpForm.getFirstInstallmentDate());
            cp.setLastInstallmentDate(cpForm.getLastInstallmentDate());
            cp.setPack(pack.get());
            cp.setRequest(request.get());
            cpRepository.save(cp);
            return true;
        }
        return false;
    }

    public boolean saveConsumptionPack(ConsumptionPack consumptionPack) {
        ConsumptionPack cp = cpRepository.save(consumptionPack);
        if (cp != null)
            return true;
        else
            return false;

    }

    public Optional<ConsumptionPack> findByUid(String cpUid) {
        Optional<ConsumptionPack> cp = cpRepository.findByUid(UUID.fromString(cpUid));
        if (cp.isPresent())
            return cp;
        else
            return Optional.empty();
    }

    public Optional<List<ConsumptionPack>> findAllByRequestAndPack(String requestUid, String packUid) {
        Optional<List<ConsumptionPack>> cpList = cpRepository.findConsumptionPacksByRequestUidAndPackUid(UUID.fromString(requestUid), UUID.fromString(packUid));
        if (cpList.isPresent()) {

        }
        return Optional.empty();
    }

    public Optional<List<ConsumptionPack>> findAllByRequest(String requestUid) {
        Optional<List<ConsumptionPack>> cpList = cpRepository.findConsumptionPacksByRequestUid(UUID.fromString(requestUid));
        if (cpList.isPresent()) {

        }
        return Optional.empty();
    }

    public Optional<List<ConsumptionPack>> findAllByPack(String packUid) {
        Optional<List<ConsumptionPack>> cpList = cpRepository.findConsumptionPacksByPackUid(UUID.fromString(packUid));
        if (cpList.isPresent()) {

        }
        return Optional.empty();
    }

}
