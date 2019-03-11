package ir.mostashar.model.pack.service;

import ir.mostashar.model.adviceType.AdviceType;
import ir.mostashar.model.adviceType.service.AdviceTypeService;
import ir.mostashar.model.assignDiscount.AssignDiscount;
import ir.mostashar.model.assignDiscount.dto.AssignDiscountForm;
import ir.mostashar.model.assignDiscount.service.AssignDiscountService;
import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.service.ClientService;
import ir.mostashar.model.consumptionPack.ConsumptionPack;
import ir.mostashar.model.consumptionPack.service.ConsumptionPackService;
import ir.mostashar.model.discountPack.DiscountPack;
import ir.mostashar.model.discountPack.service.DiscountPackService;
import ir.mostashar.model.factor.service.FactorService;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.service.LawyerService;
import ir.mostashar.model.pack.BuyPack;
import ir.mostashar.model.pack.BuyPackStatus;
import ir.mostashar.model.pack.Pack;
import ir.mostashar.model.pack.dto.ListPackDTO;
import ir.mostashar.model.pack.dto.PackDTO;
import ir.mostashar.model.pack.dto.BuyPackForm;
import ir.mostashar.model.pack.repository.PackRepo;
import ir.mostashar.model.packsnapshot.PackSnapshot;
import ir.mostashar.model.packsnapshot.service.PackSnapshotService;
import ir.mostashar.model.request.Request;
import ir.mostashar.model.request.service.RequestService;
import ir.mostashar.utils.Constants;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PackService {

    @Autowired
    PackRepo packRepo;

//    @Autowired
//    AdviceTypeRepo adviceTypeRepo;

    @Autowired
    AdviceTypeService atService;

    @Autowired
    ConsumptionPackService cpService;

    @Autowired
    PackSnapshotService psService;

    @Autowired
    FactorService factorService;

    @Autowired
    RequestService requestService;

    @Autowired
    LawyerService lawyerService;

    @Autowired
    ClientService clientService;

    @Autowired
    AssignDiscountService adService;

    @Autowired
    DiscountPackService dpService;

    /**
     * first find Advicetype by callId
     * two assing to setAdvicetype
     * three save new Pack
     * four retrun true
     *
     * @param buyPackForm
     */
    public boolean createPack(BuyPackForm buyPackForm) {
        UUID uuid = UUID.randomUUID();
        Optional<AdviceType> adviceType = atService.findAdviceTypeByUid(buyPackForm.getAdviceTypeId());
        Optional<Boolean> existsPackByName = packRepo.existsPackByName(buyPackForm.getName());
        if (existsPackByName.isPresent() && !existsPackByName.get()) {
            if (adviceType.isPresent()) {
                Pack pack = new Pack();
                pack.setUid(uuid);
                pack.setMinute(buyPackForm.getMinute());
                pack.setDescription(buyPackForm.getDescription());
                pack.setActive(false);
                pack.setAdvicetype(adviceType.get());
                packRepo.save(pack);
                return true;
            }
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

    public boolean updatePack(BuyPackForm buyPackForm) {
        Optional<Pack> pack = packRepo.findPackByUid(UUID.fromString(buyPackForm.getUid()));
        if (pack.isPresent()) {
            Optional<AdviceType> adviceType = atService.findAdviceTypeByUid(buyPackForm.getAdviceTypeId());
            pack.get().setName(buyPackForm.getUid());
            pack.get().setDescription(buyPackForm.getDescription());
            pack.get().setActive(buyPackForm.isActive());
            pack.get().setMinute(buyPackForm.getMinute());
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
            packDTO.setActive(pack.get().getActive());
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
            packObj.setActive(pack.getActive());

            listPackDTO.add(packObj);
        }
        if (packList != null)
            return Optional.of(new ListPackDTO(HttpStatus.OK.value(), Constants.KEY_SUCESSE, listPackDTO));
        else
            return Optional.empty();
    }

    @Transactional
    public Optional<BuyPackStatus> createBuyPack(BuyPackForm bpForm) {
//        UUID factorId;
        UUID consumptionPackUid;
        Optional<Pack> pack = packRepo.findPackByUid(UUID.fromString(bpForm.getPackId()));
        Optional<Request> request = requestService.findByUid(bpForm.getRequestId());
        Optional<AdviceType> adviceType = atService.findAdviceTypeByUid(bpForm.getAdviceTypeId());
        Optional<Lawyer> lawyer = lawyerService.findByUid(bpForm.getLawyerId());
        Optional<Client> client = clientService.findClientByUidAndActive(bpForm.getUserId(), true);

        System.out.println("Log---createBuyPack--------------------:" + pack.isPresent());
        System.out.println("Log---createBuyPack--------------------:" + request.isPresent());
        System.out.println("Log---createBuyPack--------------------:" + adviceType.isPresent());
        System.out.println("Log---createBuyPack--------------------:" + lawyer.isPresent());
        System.out.println("Log---createBuyPack--------------------:" + client.isPresent());

        if (pack.isPresent() && request.isPresent() && adviceType.isPresent() && lawyer.isPresent() && client.isPresent()) {

            // insert into consumptionPack
            ConsumptionPack consumptionPack = new ConsumptionPack();
            consumptionPackUid = UUID.randomUUID();
            consumptionPack.setUid(consumptionPackUid);
            consumptionPack.setConsumptionTime(0L);
            consumptionPack.setBaseTime(bpForm.getMinute());
            consumptionPack.setType(adviceType.get().getType());
            consumptionPack.setFirstInstallmentDate(System.currentTimeMillis()); // سوال شود این چیه
            consumptionPack.setLastInstallmentDate(0L); // سوال شود این چیه
            consumptionPack.setPack(pack.get());
            consumptionPack.setRequest(request.get());
            if (!cpService.saveConsumptionPack(consumptionPack))
                return Optional.ofNullable(new BuyPackStatus(BuyPack.ConsumptionPackError));

            // insert into packsanpshot

            PackSnapshot psShot = new PackSnapshot();
            psShot.setUid(UUID.randomUUID());
            psShot.setPackName(pack.get().getName());
            psShot.setPackDescription(bpForm.getDescription());
            psShot.setPackMinute(pack.get().getMinute());
            psShot.setLawyerPricePerMinute(bpForm.getLawyerPricePerMinute());


            psShot.setTotalPrice(bpForm.getTotalPrice());
            psShot.setActive(bpForm.isActive()); // روی چه حالتی باشه
            psShot.setAdvicetype(adviceType.get());
            psShot.setLawyer(lawyer.get());
            addScore(bpForm.getLawyerId(), bpForm.getCodeOff());
            if (!psService.savePackSnapShot(psShot))
                return Optional.ofNullable(new BuyPackStatus(BuyPack.PackSnapshotError));

            // Add Off اعمال کد تخفیف
            addScore(bpForm.getUserId(), bpForm.getCodeOff());
            // insert into factor

            /*Factor factor = new Factor();
            factorId = UUID.randomUUID();
            factor.setFactorId(factorId);
            factor.setServiceDescription(""); //از کجا پر میشه
            factor.setClientName(client.get().getFirstName() + " " + client.get().getLastName());
            factor.setClientCode(""); // پرسیده شود
            factor.setAddress(client.get().getAddress());
            factor.setTel(client.get().getTel());
            factor.setPostalCode(client.get().getPostalCode());
            factor.setFactorNumber((factorService.getMaxFactorNumber() + 1) + "");
            factor.setCreationDate(System.currentTimeMillis());
            factor.setValue(bpForm.getTotalPrice()); // پرسیده شود
            if (!factorService.saveFactor(factor))
                return Optional.ofNullable(new BuyPackStatus(BuyPack.FactorError));*/

            return Optional.ofNullable(new BuyPackStatus(BuyPack.ComplateAll));
        }
        return Optional.ofNullable(new BuyPackStatus(BuyPack.ErrorAll));
    }

    private void addScore(String userUid, String codeOff) {
        if (TextUtils.isEmpty(codeOff) || TextUtils.isEmpty(userUid))
            return;

        Optional<DiscountPack> discountPack = dpService.findByCodeOff(codeOff);
        if (discountPack.isPresent()) {
            AssignDiscountForm assignDiscountForm = new AssignDiscountForm();
            assignDiscountForm.setDiscountPackId(discountPack.get().getUid().toString());
            assignDiscountForm.setUserId(userUid);
            adService.createAssignDiscount(assignDiscountForm);
            clientService.addScore(userUid, discountPack.get().getValue());
        }
    }

}
