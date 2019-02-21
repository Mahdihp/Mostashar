package ir.mostashar.model.bill.service;

import ir.mostashar.model.bill.Bill;
import ir.mostashar.model.bill.dto.BillDTO;
import ir.mostashar.model.bill.dto.BillForm;
import ir.mostashar.model.bill.dto.ListBillDTO;
import ir.mostashar.model.bill.repository.BillRepo;
import ir.mostashar.model.factor.Factor;
import ir.mostashar.model.factor.service.FactorService;
import ir.mostashar.model.wallet.Wallet;
import ir.mostashar.model.wallet.repository.WalletRepo;
import ir.mostashar.model.wallet.service.WalletService;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BillService {

    @Autowired
    BillRepo billRepo;

    @Autowired
    WalletService walletService;

    @Autowired
    FactorService factorService;

    public boolean createBill(BillForm billForm) {
        Optional<Wallet> wallet = walletService.findByUid(billForm.getWalletUid(), false);
        Optional<Factor> factor = factorService.findByUid(billForm.getFactorUid());
        if (wallet.isPresent() && factor.isPresent()) {
            Bill bill = new Bill();
            bill.setUid(UUID.randomUUID());
            bill.setTrackingNumber(billForm.getTrackingNumber());
            bill.setTrackingNumber(billForm.getTrackingNumber());
            bill.setTransactionDate(billForm.getTransactionDate()); // System.currentTimeMillis()
            bill.setStatus(billForm.getStatus());
            bill.setValue(billForm.getValue());
            bill.setOrgUid(billForm.getOrgUid()); // اگر دارد
            bill.setWallet(wallet.get());
            bill.setFactor(factor.get());
            billRepo.save(bill);
            return true;
        }
        return false;
    }

    public Optional<Bill> findBillByUid(String uid) {
        Optional<Bill> bill = billRepo.findByUid(UUID.fromString(uid));
        if (bill.isPresent())
            return Optional.ofNullable(bill.get());
        else
            return Optional.empty();
    }

    public Optional<BillDTO> findBillDTOByValue(Long value) {
        Optional<Bill> bill = billRepo.findByValue(value);
        if (bill.isPresent()) {
            BillDTO billDTO = new BillDTO();
            billDTO.setStatus(HttpStatus.OK.value());
            billDTO.setMessage(Constants.KEY_SUCESSE);
            billDTO.setUid(bill.get().toString());
            billDTO.setTrackingNumber(bill.get().getTrackingNumber());
            billDTO.setTrackingNumber(bill.get().getTrackingNumber());
            billDTO.setTransactionDate(bill.get().getTransactionDate()); // System.currentTimeMillis()
            billDTO.setStatus(bill.get().getStatus());
            billDTO.setValue(bill.get().getValue());
            billDTO.setOrgUid(bill.get().getOrgUid()); // اگر دارد
            billDTO.setWalletUid(bill.get().getWallet().getUid().toString());
            billDTO.setFactorUid(bill.get().getFactor().getUid().toString());
            return Optional.ofNullable(billDTO);
        }
        return Optional.empty();
    }

    public Optional<BillDTO> findBillDTOByTrackingNumber(String trackingNumber) {
        Optional<Bill> bill = billRepo.findByTrackingNumber(trackingNumber);
        if (bill.isPresent()) {
            BillDTO billDTO = new BillDTO();
            billDTO.setStatus(HttpStatus.OK.value());
            billDTO.setMessage(Constants.KEY_SUCESSE);
            billDTO.setUid(bill.get().toString());
            billDTO.setTrackingNumber(bill.get().getTrackingNumber());
            billDTO.setTrackingNumber(bill.get().getTrackingNumber());
            billDTO.setTransactionDate(bill.get().getTransactionDate()); // System.currentTimeMillis()
            billDTO.setStatus(bill.get().getStatus());
            billDTO.setValue(bill.get().getValue());
            billDTO.setOrgUid(bill.get().getOrgUid()); // اگر دارد
            billDTO.setWalletUid(bill.get().getWallet().getUid().toString());
            billDTO.setFactorUid(bill.get().getFactor().getUid().toString());
            return Optional.ofNullable(billDTO);
        }
        return Optional.empty();
    }

    public Optional<BillDTO> findBillDTOByTransactionNumber(String transactionNumber) {
        Optional<Bill> bill = billRepo.findByTransactionNumber(transactionNumber);
        if (bill.isPresent()) {
            BillDTO billDTO = new BillDTO();
            billDTO.setStatus(HttpStatus.OK.value());
            billDTO.setMessage(Constants.KEY_SUCESSE);
            billDTO.setUid(bill.get().toString());
            billDTO.setTrackingNumber(bill.get().getTrackingNumber());
            billDTO.setTrackingNumber(bill.get().getTrackingNumber());
            billDTO.setTransactionDate(bill.get().getTransactionDate()); // System.currentTimeMillis()
            billDTO.setStatus(bill.get().getStatus());
            billDTO.setValue(bill.get().getValue());
            billDTO.setOrgUid(bill.get().getOrgUid()); // اگر دارد
            billDTO.setWalletUid(bill.get().getWallet().getUid().toString());
            billDTO.setFactorUid(bill.get().getFactor().getUid().toString());
            return Optional.ofNullable(billDTO);
        }
        return Optional.empty();
    }

    public Optional<BillDTO> findBillDTOByUid(String uid) {
        Optional<Bill> bill = billRepo.findByUid(UUID.fromString(uid));
        if (bill.isPresent()) {
            BillDTO billDTO = new BillDTO();
            billDTO.setStatus(HttpStatus.OK.value());
            billDTO.setMessage(Constants.KEY_SUCESSE);
            billDTO.setUid(bill.get().toString());
            billDTO.setTrackingNumber(bill.get().getTrackingNumber());
            billDTO.setTrackingNumber(bill.get().getTrackingNumber());
            billDTO.setTransactionDate(bill.get().getTransactionDate()); // System.currentTimeMillis()
            billDTO.setStatus(bill.get().getStatus());
            billDTO.setValue(bill.get().getValue());
            billDTO.setOrgUid(bill.get().getOrgUid()); // اگر دارد
            billDTO.setWalletUid(bill.get().getWallet().getUid().toString());
            billDTO.setFactorUid(bill.get().getFactor().getUid().toString());
            return Optional.ofNullable(billDTO);
        }
        return Optional.empty();
    }

    public Optional<ListBillDTO> findListBillDTOByTransactionDate(Long transactionDate) {
        Optional<List<Bill>> bills = billRepo.findByTransactionDate(transactionDate);
        if (bills.isPresent()) {
            ListBillDTO lbDTO = new ListBillDTO();
            lbDTO.setStatus(HttpStatus.OK.value());
            lbDTO.setMessage(Constants.KEY_SUCESSE);
            List<BillDTO> dtoList = new ArrayList<>();
            for (Bill bill : bills.get()) {
                BillDTO billDTO = new BillDTO();
                billDTO.setUid(bill.toString());
                billDTO.setTrackingNumber(bill.getTrackingNumber());
                billDTO.setTrackingNumber(bill.getTrackingNumber());
                billDTO.setTransactionDate(bill.getTransactionDate()); // System.currentTimeMillis()
                billDTO.setStatus(bill.getStatus());
                billDTO.setValue(bill.getValue());
                billDTO.setOrgUid(bill.getOrgUid()); // اگر دارد
                billDTO.setWalletUid(bill.getWallet().getUid().toString());
                billDTO.setFactorUid(bill.getFactor().getUid().toString());
                dtoList.add(billDTO);
            }
            lbDTO.setData(dtoList);
            return Optional.ofNullable(lbDTO);
        }
        return Optional.empty();
    }
}
