package ir.mostashar.model.bill.service;

import ir.mostashar.model.bill.Bill;
import ir.mostashar.model.bill.dto.BillForm;
import ir.mostashar.model.bill.repository.BillRepo;
import ir.mostashar.model.factor.Factor;
import ir.mostashar.model.factor.service.FactorService;
import ir.mostashar.model.wallet.Wallet;
import ir.mostashar.model.wallet.repository.WalletRepo;
import ir.mostashar.model.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<Bill> findByUid(String uid) {
        Optional<Bill> bill = billRepo.findByUid(UUID.fromString(uid));
        if (bill.isPresent())
            return Optional.ofNullable(bill.get());
        else
            return Optional.empty();
    }
}
