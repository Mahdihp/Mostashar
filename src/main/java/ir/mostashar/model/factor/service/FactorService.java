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
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${mostashar.app.factorNumber}")
    private String factorNumber;


    public boolean createFactor(FactorForm factorForm) {
        Optional<Bill> bill = billService.findBillByUid(factorForm.getBillUid());
        if (bill.isPresent()) {
            Factor factor = new Factor();
            factor.setUid(UUID.randomUUID());
            factor.setServiceDescription(factorForm.getServiceDescription());
            factor.setClientName(factorForm.getClientName());
//            factor.setClientCode(factorForm.getClientCode());
            factor.setAddress(factorForm.getAddress());
            factor.setTel(factorForm.getTel());
            factor.setPostalCode(factorForm.getPostalCode());

            Long maxFactorNumber = factorRepo.findMaxFactorNumber();
            if (maxFactorNumber != null) {
                factor.setFactorNumber(String.valueOf(factorNumber + 1));
            } else {
                factor.setFactorNumber(factorNumber);
            }

            factor.setFactorNumber(factorForm.getFactorNumber()); // چگونه تولید می شود؟
            factor.setCreationDate(System.currentTimeMillis());
            factor.setValue(factorForm.getValue());
            factor.setBill(bill.get());
            factorRepo.save(factor);
            return true;
        }
        return false;
    }

    public boolean saveFactor(Factor factor) {
        Factor save = factorRepo.save(factor);
        if (save != null)
            return true;
        else
            return false;
    }

    public Long getMaxFactorNumber() {
        Long maxFactorNumber = factorRepo.findMaxFactorNumber();
        if (maxFactorNumber != null)
            return maxFactorNumber;
        else
            return null;
    }

    public boolean updateFactor(FactorForm factorForm) {
        Optional<Factor> factor = factorRepo.findByUid(UUID.fromString(factorForm.getUid()));
        Optional<Bill> bill = billService.findBillByUid(factorForm.getBillUid());
        if (factor.isPresent() && bill.isPresent()) {
            factor.get().setServiceDescription(factorForm.getServiceDescription());
            factor.get().setClientName(factorForm.getClientName());
//            factor.get().setClientCode(factorForm.getClientCode());
            factor.get().setAddress(factorForm.getAddress());
            factor.get().setTel(factorForm.getTel());
            factor.get().setPostalCode(factorForm.getPostalCode());
            factor.get().setFactorNumber(factorForm.getFactorNumber()); // چگونه تولید می شود؟
            factor.get().setCreationDate(System.currentTimeMillis());
            factor.get().setValue(factorForm.getValue());
            factor.get().setBill(bill.get());
            factorRepo.save(factor.get());
            return true;
        }
        return false;
    }

    public boolean deleteFactor(String uid) {
        Optional<Factor> factor = factorRepo.findByUid(UUID.fromString(uid));
        if (factor.isPresent()) {
            factorRepo.delete(factor.get());
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
//            factorDTO.setClientCode(factor.get().getClientCode());
            factorDTO.setAddress(factor.get().getAddress());
            factorDTO.setTel(factor.get().getTel());
            factorDTO.setPostalCode(factor.get().getPostalCode());
            factorDTO.setFactorNumber(factor.get().getFactorNumber()); // چگونه تولید می شود؟
            factorDTO.setCreationDate(System.currentTimeMillis());
            factorDTO.setValue(factor.get().getValue());
            factorDTO.setBillUid(factor.get().getBill().getUid().toString());

            return Optional.ofNullable(factorDTO);
        }
        return Optional.empty();
    }

    public Optional<ListFactorDTO> findListFactorDTO(Long createDate, String userUid_factorNumber, short typeQuer) {
        Optional<List<Factor>> factors = Optional.empty();
        switch (typeQuer) {
            case 1: // find by user id
                factors = Optional.empty();
                break;
            case 2:
                factors = factorRepo.findAllByFactorNumberAndDeleted(userUid_factorNumber, false);
                break;
            case 3:
                factors = factorRepo.findAllByCreationDateAndDeleted(createDate, false);
                break;
        }
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
//                factorDTO.setClientCode(factor.getClientCode());
                factorDTO.setAddress(factor.getAddress());
                factorDTO.setTel(factor.getTel());
                factorDTO.setPostalCode(factor.getPostalCode());
                factorDTO.setFactorNumber(factor.getFactorNumber());
                factorDTO.setCreationDate(System.currentTimeMillis());
                factorDTO.setValue(factor.getValue());
                factorDTO.setBillUid(factor.getBill().getUid().toString());
                dtoList.add(factorDTO);
            }
            lfDTO.setData(dtoList);
            return Optional.ofNullable(lfDTO);
        }
        return Optional.empty();
    }


}
