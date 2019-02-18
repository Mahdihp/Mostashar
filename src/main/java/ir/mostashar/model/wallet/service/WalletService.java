package ir.mostashar.model.wallet.service;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.organization.Organization;
import ir.mostashar.model.organization.repository.OrganizationRepository;
import ir.mostashar.model.user.User;
import ir.mostashar.model.user.repository.UserRepository;
import ir.mostashar.model.wallet.Wallet;
import ir.mostashar.model.wallet.dto.WalletForm;
import ir.mostashar.model.wallet.repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.event.CaretListener;
import java.util.Optional;
import java.util.UUID;

@Service
public class WalletService {


    @Autowired
    WalletRepository walletRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrganizationRepository orgRepository;

    /**
     * Find User Uid & later create & Save new Wallet Object
     *
     * @param walletForm
     * @return
     */
    public boolean createWallet(WalletForm walletForm) {
        Optional<User> user = userRepository.findUserByUid(UUID.fromString(walletForm.getUserId()));
        if (user.isPresent()) {
            Wallet wallet = new Wallet();
            wallet.setUid(UUID.randomUUID());
            wallet.setBankAccountName(walletForm.getBankAccountName());
            wallet.setBankAccountNumber(walletForm.getBankAccountNumber());
            wallet.setBankAccountSheba(walletForm.getBankAccountSheba());
            wallet.setValue(walletForm.getValue());
            Optional<Organization> organization = orgRepository.findByUid(UUID.fromString(walletForm.getOrganizationId()));
            if (organization.isPresent())
                wallet.setOrganization(organization.get());

            wallet.setUser(user.get());
            walletRepository.save(wallet);
            return true;
        }
        return false;
    }

    public boolean addMoneyWallet(String walletUid, String userUid, int money) {
        Optional<User> user = userRepository.findUserByUid(UUID.fromString(userUid));
        if (user.isPresent()) {
            Optional<Wallet> wallet = walletRepository.findByUidAndUserUidAndDeleted(UUID.fromString(walletUid), user.get().getUid(), false);
            if (wallet.isPresent()) {
                int moneyWallet = wallet.get().getValue();
                moneyWallet += money;
                wallet.get().setValue(moneyWallet);
                walletRepository.save(wallet.get());
                return true;
            }
        }
        return false;
    }

    public boolean minusMoneyWallet(String walletUid, String userUid, int money) {
        Optional<User> user = userRepository.findUserByUid(UUID.fromString(userUid));
        if (user.isPresent()) {
            Optional<Wallet> wallet = walletRepository.findByUidAndUserUidAndDeleted(UUID.fromString(walletUid), user.get().getUid(), false);
            if (wallet.isPresent()) {
                int moneyWallet = wallet.get().getValue();
                moneyWallet -= money;
                wallet.get().setValue(moneyWallet);
                walletRepository.save(wallet.get());
                return true;
            }
        }
        return false;
    }

    public boolean updateWallet(WalletForm walletForm) {
        Optional<Wallet> wallet = walletRepository.findByUidAndUserUidAndDeleted(UUID.fromString(walletForm.getWalletId()), UUID.fromString(walletForm.getUserId()), false);
        if (wallet.isPresent()) {
            wallet.get().setBankAccountName(walletForm.getBankAccountName());
            wallet.get().setBankAccountNumber(walletForm.getBankAccountNumber());
            wallet.get().setBankAccountSheba(walletForm.getBankAccountSheba());
            wallet.get().setValue(walletForm.getValue());
            Optional<Organization> organization = orgRepository.findByUid(UUID.fromString(walletForm.getOrganizationId()));
            if (organization.isPresent())
                wallet.get().setOrganization(organization.get());

            walletRepository.save(wallet.get());
            return true;
        }
        return false;
    }

    public boolean deleteWallet(String walletUid, String userUid) {
        Optional<Wallet> wallet = walletRepository.findByUidAndUserUidAndDeleted(UUID.fromString(walletUid), UUID.fromString(userUid), false);
        if (wallet.isPresent()) {
            wallet.get().setDeleted(true);
            walletRepository.save(wallet.get());
            return true;
        }
        return false;
    }

    public boolean restoreWallet(String walletUid, String userUid){
        Optional<Wallet> wallet = walletRepository.findByUidAndUserUid(UUID.fromString(walletUid), UUID.fromString(userUid));
        if (wallet.isPresent()) {
            wallet.get().setDeleted(false);
            walletRepository.save(wallet.get());
            return true;
        }
        return false;
    }

}
