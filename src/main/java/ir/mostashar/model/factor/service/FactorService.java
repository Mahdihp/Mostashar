package ir.mostashar.model.factor.service;

import ir.mostashar.model.bill.Bill;
import ir.mostashar.model.bill.service.BillService;
import ir.mostashar.model.client.service.ClientService;
import ir.mostashar.model.factor.Factor;
import ir.mostashar.model.factor.dto.FactorDTO;
import ir.mostashar.model.factor.dto.FactorForm;
import ir.mostashar.model.factor.dto.ListFactorDTO;
import ir.mostashar.model.factor.repository.FactorRepo;
import ir.mostashar.model.installment.Installment;
import ir.mostashar.model.installment.service.InstallmentService;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FactorService {

    @Autowired
    FactorRepo factorRepo;

    @Autowired
    BillService billService;

    @Autowired
    InstallmentService installmentService;

    @Autowired
    ClientService clientService;


    public boolean createFactor(FactorForm factorForm) {
        Optional<Bill> bill = billService.findBillByUid(factorForm.getBillUid());
        Optional<Installment> installment = installmentService.findInstallmentByUid(factorForm.getInstallmentUid());
        if (bill.isPresent() && installment.isPresent()) {
            Factor factor = new Factor();
            factor.setUid(UUID.randomUUID());
            factor.setServiceDescription(factorForm.getServiceDescription());
            factor.setClientName(factorForm.getClientName());
            factor.setClientCode(factorForm.getClientCode());
            factor.setAddress(factorForm.getAddress());
            factor.setTel(factorForm.getTel());
            factor.setPostalCode(factorForm.getPostalCode());
            factor.setFactorNumber(factorForm.getFactorNumber()); // چگونه تولید می شود؟
            factor.setCreationDate(System.currentTimeMillis());
            factor.setValue(factorForm.getValue());
            factor.setBill(bill.get());
            factor.setInstallment(installment.get());
            factorRepo.save(factor);
            return true;
        }
        return false;
    }

    public Optional<Factor> findByUid(String uid) {
        Optional<Factor> factor = factorRepo.findByUid(UUID.fromString(uid));
        if (factor.isPresent()) {
            return Optional.ofNullable(factor.get());
        }
        return Optional.empty();
    }

    public Optional<FactorDTO> findFactorDTOByUid(String uid) {
        Optional<Factor> factor = factorRepo.findByUid(UUID.fromString(uid));
        if (factor.isPresent()) {
            FactorDTO factorDTO = new FactorDTO();
            factorDTO.setStatus(HttpStatus.OK.value());
            factorDTO.setMessage(Constants.KEY_SUCESSE);

            factorDTO.setUid(factor.get().getUid().toString());
            factorDTO.setServiceDescription(factor.get().getServiceDescription());
            factorDTO.setClientName(factor.get().getClientName());
            factorDTO.setClientCode(factor.get().getClientCode());
            factorDTO.setAddress(factor.get().getAddress());
            factorDTO.setTel(factor.get().getTel());
            factorDTO.setPostalCode(factor.get().getPostalCode());
            factorDTO.setFactorNumber(factor.get().getFactorNumber()); // چگونه تولید می شود؟
            factorDTO.setCreationDate(System.currentTimeMillis());
            factorDTO.setValue(factor.get().getValue());
            factorDTO.setBillUid(factor.get().getBill().getUid().toString());
            factorDTO.setInstallmentUid(factor.get().getInstallment().getUid().toString());
            return Optional.ofNullable(factorDTO);
        }
        return Optional.empty();
    }

    public Optional<ListFactorDTO> findListFactorDTOByFactorNumber(String factorNumber) {
        Optional<List<Factor>> factors = factorRepo.findAllByFactorNumberAndDeleted(factorNumber, false);
        if (factors.isPresent()) {
            ListFactorDTO lfDTO = new ListFactorDTO();
            lfDTO.setStatus(HttpStatus.OK.value());
            lfDTO.setMessage(Constants.KEY_SUCESSE);
            List<FactorDTO> dtoList = new ArrayList<>();
            for (Factor factor : factors.get()) {
                FactorDTO factorDTO = new FactorDTO();
                factorDTO.setUid(factor.getUid().toString());
                factorDTO.setServiceDescription(factor.getServiceDescription());
                factorDTO.setClientName(factor.getClientName());
                factorDTO.setClientCode(factor.getClientCode());
                factorDTO.setAddress(factor.getAddress());
                factorDTO.setTel(factor.getTel());
                factorDTO.setPostalCode(factor.getPostalCode());
                factorDTO.setFactorNumber(factor.getFactorNumber());
                factorDTO.setCreationDate(System.currentTimeMillis());
                factorDTO.setValue(factor.getValue());
                factorDTO.setBillUid(factor.getBill().getUid().toString());
                factorDTO.setInstallmentUid(factor.getInstallment().getUid().toString());
                dtoList.add(factorDTO);
            }
            lfDTO.setData(dtoList);
            return Optional.ofNullable(lfDTO);
        }
        return Optional.empty();
    }
    public Optional<ListFactorDTO> findListFactorDTOByCraeteDate(Long createDate) {
        Optional<List<Factor>> factors = factorRepo.findAllByCreationDateAndDeleted(createDate, false);
        if (factors.isPresent()) {
            ListFactorDTO lfDTO = new ListFactorDTO();
            lfDTO.setStatus(HttpStatus.OK.value());
            lfDTO.setMessage(Constants.KEY_SUCESSE);
            List<FactorDTO> dtoList = new ArrayList<>();
            for (Factor factor : factors.get()) {
                FactorDTO factorDTO = new FactorDTO();
                factorDTO.setUid(factor.getUid().toString());
                factorDTO.setServiceDescription(factor.getServiceDescription());
                factorDTO.setClientName(factor.getClientName());
                factorDTO.setClientCode(factor.getClientCode());
                factorDTO.setAddress(factor.getAddress());
                factorDTO.setTel(factor.getTel());
                factorDTO.setPostalCode(factor.getPostalCode());
                factorDTO.setFactorNumber(factor.getFactorNumber());
                factorDTO.setCreationDate(System.currentTimeMillis());
                factorDTO.setValue(factor.getValue());
                factorDTO.setBillUid(factor.getBill().getUid().toString());
                factorDTO.setInstallmentUid(factor.getInstallment().getUid().toString());
                dtoList.add(factorDTO);
            }
            lfDTO.setData(dtoList);
            return Optional.ofNullable(lfDTO);
        }
        return Optional.empty();
    }


}
