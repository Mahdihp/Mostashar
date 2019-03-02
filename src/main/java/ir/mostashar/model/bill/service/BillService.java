package ir.mostashar.model.bill.service;

import ir.mostashar.model.bill.Bill;
import ir.mostashar.model.bill.dto.BillDTO;
import ir.mostashar.model.bill.dto.BillForm;
import ir.mostashar.model.bill.dto.ListBillDTO;
import ir.mostashar.model.bill.repository.BillRepo;
import ir.mostashar.model.factor.Factor;
import ir.mostashar.model.factor.service.FactorService;
import ir.mostashar.model.wallet.Wallet;
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

    public boolean createBill(BillForm billForm) {
        Optional<Wallet> wallet = walletService.findByUid(billForm.getWalletUid(), false);
        Optional<Boolean> exists = billRepo.existsByTransactionNumber(billForm.getTransactionNumber());

        if (wallet.isPresent() && exists.isPresent() && !exists.get()) {
            Bill bill = new Bill();
            bill.setUid(UUID.randomUUID());
            bill.setTransactionNumber(billForm.getTransactionNumber());
            bill.setTrackingNumber(billForm.getTrackingNumber());
            bill.setTransactionDate(billForm.getTransactionDate()); // System.currentTimeMillis()
            bill.setBillStatus(billForm.getBillStatus());
            bill.setValue(billForm.getValue());
            bill.setOrgUid(billForm.getOrgUid()); // اگر دارد
            bill.setWallet(wallet.get());
            billRepo.save(bill);
            return true;
        }
        return false;
    }

    public boolean updateBill(BillForm billForm) {
        Optional<Wallet> wallet = walletService.findByUid(billForm.getWalletUid(), false);
        Optional<Bill> bill = billRepo.findByUid(UUID.fromString(billForm.getUid()));
        if (bill.isPresent() && wallet.isPresent()) {
            bill.get().setTransactionNumber(billForm.getTransactionNumber());
            bill.get().setTrackingNumber(billForm.getTrackingNumber());
            bill.get().setTransactionDate(billForm.getTransactionDate()); // System.currentTimeMillis()
            bill.get().setBillStatus(billForm.getBillStatus());
            bill.get().setValue(billForm.getValue());
            bill.get().setOrgUid(billForm.getOrgUid()); // اگر دارد
            bill.get().setWallet(wallet.get());
            billRepo.save(bill.get());
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

    public Optional<BillDTO> findBillDTO(Long value, String trackingNumber_TransactionNumber_Uid, short type) {
        Optional<Bill> bill = Optional.empty();
        switch (type) {
            case 1:
                bill = billRepo.findByValue(value);
                break;
            case 2:
                bill = billRepo.findByTrackingNumber(trackingNumber_TransactionNumber_Uid); //trackingNumber
                break;
            case 3:
                bill = billRepo.findByTransactionNumber(trackingNumber_TransactionNumber_Uid); //transactionNumber
                break;
            case 4:
                bill = billRepo.findByUid(UUID.fromString(trackingNumber_TransactionNumber_Uid)); //id
                break;
        }
        if (bill.isPresent()) {
            BillDTO billDTO = new BillDTO();
            billDTO.setStatus(HttpStatus.OK.value());
            billDTO.setMessage(Constants.KEY_SUCESSE);
            billDTO.setUid(bill.get().toString());
            billDTO.setTransactionNumber(bill.get().getTransactionNumber());
            billDTO.setTrackingNumber(bill.get().getTrackingNumber());
            billDTO.setTransactionDate(bill.get().getTransactionDate()); // System.currentTimeMillis()
            billDTO.setBillStatus(bill.get().getBillStatus());
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
                billDTO.setTransactionNumber(bill.getTransactionNumber());
                billDTO.setTrackingNumber(bill.getTrackingNumber());
                billDTO.setTransactionDate(bill.getTransactionDate()); // System.currentTimeMillis()
                billDTO.setBillStatus(bill.getBillStatus());
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
