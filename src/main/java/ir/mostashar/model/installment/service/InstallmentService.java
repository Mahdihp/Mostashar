package ir.mostashar.model.installment.service;

import ir.mostashar.model.installment.Installment;
import ir.mostashar.model.installment.InstallmentForm;
import ir.mostashar.model.installment.dto.InstallmentDTO;
import ir.mostashar.model.installment.dto.ListInstallmentDTO;
import ir.mostashar.model.installment.repository.InstallmentRepo;
import ir.mostashar.model.pack.Pack;
import ir.mostashar.model.pack.service.PackService;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InstallmentService {

    @Autowired
    InstallmentRepo installmentRepo;

    @Autowired
    PackService packService;

    public boolean createInstallment(InstallmentForm iForm) {
        Optional<Pack> pack = packService.findPackByUid(iForm.getConsumptionPackUid());
        Optional<Boolean> exists = installmentRepo.existsByInstallmentNumber(iForm.getInstallmentNumber());
        if (exists.isPresent() && !exists.get()) {
            if (pack.isPresent()) {
                Installment installment = new Installment();
                installment.setUid(UUID.randomUUID());
                installment.setCreationDate(System.currentTimeMillis());
                installment.setInstallmentNumber(iForm.getInstallmentNumber());
                installment.setInstallmentTotalNumber(iForm.getInstallmentTotalNumber());
                installment.setValue(iForm.getValue());
                installmentRepo.save(installment);
                return true;
            }
        }
        return false;
    }

    public boolean updateInstallment(InstallmentForm iForm) {
        Optional<Installment> installment = installmentRepo.findByUid(UUID.fromString(iForm.getUid()));
        Optional<Pack> pack = packService.findPackByUid(iForm.getConsumptionPackUid());
        if (installment.isPresent() && pack.isPresent()) {
            installment.get().setCreationDate(iForm.getCreationDate());
            installment.get().setInstallmentNumber(iForm.getInstallmentNumber());
            installment.get().setInstallmentTotalNumber(iForm.getInstallmentTotalNumber());
            installment.get().setValue(iForm.getValue());
            installmentRepo.save(installment.get());
            return true;
        }
        return false;
    }

    public Optional<Installment> findInstallmentByUid(String uid) {
        Optional<Installment> installment = installmentRepo.findByUid(UUID.fromString(uid));
        if (installment.isPresent())
            return Optional.ofNullable(installment.get());
        else
            return Optional.empty();
    }

    public Optional<InstallmentDTO> findInstallmentDTOByUid(String uid) {
        Optional<Installment> installment = installmentRepo.findByUid(UUID.fromString(uid));
        if (installment.isPresent()) {
            InstallmentDTO installmentDTO = new InstallmentDTO();
            installmentDTO.setStatus(HttpStatus.OK.value());
            installmentDTO.setMessage(Constants.KEY_SUCESSE);
            installmentDTO.setUid(installment.get().getUid().toString());
            installmentDTO.setCreationDate(installment.get().getCreationDate());
            installmentDTO.setInstallmentNumber(installment.get().getInstallmentNumber());
            installmentDTO.setInstallmentTotalNumber(installment.get().getInstallmentTotalNumber());
            installmentDTO.setValue(installment.get().getValue());
            return Optional.ofNullable(installmentDTO);
        }
        return Optional.empty();
    }

    public Optional<ListInstallmentDTO> findListInstallmentDTOByConsumptionPack(String cpUid) {
        Optional<List<Installment>> list = installmentRepo.findByConsumptionpackUid(UUID.fromString(cpUid));
        if (list.isPresent()) {
            ListInstallmentDTO liDTO = new ListInstallmentDTO();
            liDTO.setStatus(HttpStatus.OK.value());
            liDTO.setMessage(Constants.KEY_SUCESSE);

            List<InstallmentDTO> dtoList = new ArrayList<>();
            for (Installment installment : list.get()) {
                InstallmentDTO dto = new InstallmentDTO();
                dto.setUid(installment.getUid().toString());
                dto.setCreationDate(installment.getCreationDate());
                dto.setInstallmentNumber(installment.getInstallmentNumber());
                dto.setInstallmentTotalNumber(installment.getInstallmentTotalNumber());
                dto.setValue(installment.getValue());
                dtoList.add(dto);
            }
            liDTO.setData(dtoList);
            return Optional.ofNullable(liDTO);
        }
        return Optional.empty();
    }
}
