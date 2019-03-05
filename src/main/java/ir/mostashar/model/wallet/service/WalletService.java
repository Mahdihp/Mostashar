package ir.mostashar.model.wallet.service;

import ir.mostashar.model.organization.Organization;
import ir.mostashar.model.organization.repository.OrganizationRepo;
import ir.mostashar.model.user.User;
import ir.mostashar.model.user.repository.UserRepo;
import ir.mostashar.model.wallet.Wallet;
import ir.mostashar.model.wallet.dto.ListWalletDTO;
import ir.mostashar.model.wallet.dto.WalletDTO;
import ir.mostashar.model.wallet.dto.WalletForm;
import ir.mostashar.model.wallet.repository.WalletRepo;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WalletService {


    @Autowired
    WalletRepo walletRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    OrganizationRepo orgRepository;

    /**
     * Find User Uid & later create & Save new Wallet Object
     *
     * @param walletForm
     * @return
     */
    public boolean createWallet(WalletForm walletForm) {
        Optional<User> user = userRepo.findUserByUid(UUID.fromString(walletForm.getUserId()));
        Optional<Boolean> exitWallet = walletRepo.existsByBankAccountNumberOrBankAccountSheba(walletForm.getBankAccountNumber(), walletForm.getBankAccountSheba());
        if (exitWallet.isPresent()) {
            if (!exitWallet.get()) {
                if (user.isPresent()) {
                    Wallet wallet = new Wallet();
                    wallet.setUid(UUID.randomUUID());
                    wallet.setBankAccountName(walletForm.getBankAccountName());
                    wallet.setBankAccountNumber(walletForm.getBankAccountNumber());
                    wallet.setBankAccountSheba(walletForm.getBankAccountSheba());
                    wallet.setValue(0);
                    Optional<Organization> organization = orgRepository.findByUid(UUID.fromString(walletForm.getOrganizationId()));
                    if (organization.isPresent())
                        wallet.setOrganization(organization.get());

                    wallet.setUser(user.get());
                    walletRepo.save(wallet);
                    return true;
                }
            }
        }
        return false;
    }

    public UUID createOrgWallet(WalletForm walletForm) {
        Optional<Wallet> wallet = walletRepo.findByOrganizationUidAndOrganizationNameAndDeleted(UUID.fromString(walletForm.getOrganizationId()), walletForm.getOrgUsername(), false);
        Optional<Organization> org = orgRepository.findByUid(UUID.fromString(walletForm.getOrganizationId()));

        UUID uid;
        if (!wallet.isPresent() && org.isPresent()) {
            Wallet newWallet = new Wallet();
            uid = UUID.randomUUID();
            newWallet.setUid(uid);
            newWallet.setBankAccountName(walletForm.getBankAccountName());
            newWallet.setBankAccountNumber(walletForm.getBankAccountNumber());
            newWallet.setBankAccountSheba(walletForm.getBankAccountSheba());
            newWallet.setValue(0);
            newWallet.setOrganization(org.get());
            walletRepo.save(newWallet);
            return uid;
        }
        return null;
    }

    public boolean saveWallet(Wallet wallet) {
        if (wallet != null) {
            walletRepo.save(wallet);
            return true;
        }
        return false;
    }

    public boolean addMoneyWallet(String walletUid, String userUid, int money) {
        Optional<User> user = userRepo.findUserByUid(UUID.fromString(userUid));
        if (user.isPresent()) {
            Optional<Wallet> wallet = walletRepo.findByUidAndUserUidAndDeleted(UUID.fromString(walletUid), user.get().getUid(), false);
            if (wallet.isPresent()) {
                int moneyWallet = wallet.get().getValue();
                moneyWallet += money;
                wallet.get().setValue(moneyWallet);
                walletRepo.save(wallet.get());
                return true;
            }
        }
        return false;
    }

    public boolean minusMoneyWallet(String walletUid, String userUid, int money) {
        Optional<User> user = userRepo.findUserByUid(UUID.fromString(userUid));
        if (user.isPresent()) {
            Optional<Wallet> wallet = walletRepo.findByUidAndUserUidAndDeleted(UUID.fromString(walletUid), user.get().getUid(), false);
            if (wallet.isPresent()) {
                int moneyWallet = wallet.get().getValue();
                moneyWallet -= money;
                wallet.get().setValue(moneyWallet);
                walletRepo.save(wallet.get());
                return true;
            }
        }
        return false;
    }

    public boolean updateWallet(WalletForm walletForm, boolean isDelete) {
        Optional<Wallet> wallet = walletRepo.findByUidAndDeleted(UUID.fromString(walletForm.getWalletId()), isDelete);
        if (wallet.isPresent()) {
            wallet.get().setBankAccountName(walletForm.getBankAccountName());
            wallet.get().setBankAccountNumber(walletForm.getBankAccountNumber());
            wallet.get().setBankAccountSheba(walletForm.getBankAccountSheba());
            Optional<Organization> organization = orgRepository.findByUid(UUID.fromString(walletForm.getOrganizationId()));
            if (organization.isPresent())
                wallet.get().setOrganization(organization.get());

            walletRepo.save(wallet.get());
            return true;
        }
        return false;
    }

    public boolean deleteWallet(String walletUid, String userUid) {
        Optional<Wallet> wallet = walletRepo.findByUidAndUserUidAndDeleted(UUID.fromString(walletUid), UUID.fromString(userUid), false);
        if (wallet.isPresent()) {
            wallet.get().setDeleted(true);
            walletRepo.save(wallet.get());
            return true;
        }
        return false;
    }

    public boolean restoreWallet(String walletUid, String userUid) {
        Optional<Wallet> wallet = walletRepo.findByUidAndUserUid(UUID.fromString(walletUid), UUID.fromString(userUid));
        if (wallet.isPresent()) {
            wallet.get().setDeleted(false);
            walletRepo.save(wallet.get());
            return true;
        }
        return false;
    }


    public Optional<Wallet> findByWalletUidAndUserUid(String walletUid, String userUid) {
        Optional<Wallet> wallet = walletRepo.findByUidAndUserUid(UUID.fromString(walletUid), UUID.fromString(userUid));
        if (wallet.isPresent()) {
            return Optional.ofNullable(wallet.get());
        }
        return Optional.empty();
    }

    public Optional<Wallet> findByUid(String uid) {
        Optional<Wallet> wallet = walletRepo.findByUid(UUID.fromString(uid));
        if (wallet.isPresent()) {
            return Optional.ofNullable(wallet.get());
        }
        return Optional.empty();
    }

    public Optional<Wallet> findByUid(String uid, boolean isDeleted) {
        Optional<Wallet> wallet = walletRepo.findByUidAndDeleted(UUID.fromString(uid), isDeleted);
        if (wallet.isPresent()) {
            return Optional.ofNullable(wallet.get());
        }
        return Optional.empty();
    }

    public Optional<Wallet> findByUid(String walletUid, String userUid, boolean isDelete) {
        Optional<Wallet> wallet = walletRepo.findByUidAndUserUidAndDeleted(UUID.fromString(walletUid), UUID.fromString(userUid), isDelete);
        if (wallet.isPresent()) {
            return Optional.ofNullable(wallet.get());
        }
        return Optional.empty();
    }

    public Optional<WalletDTO> findWalletDTOByUid(String walletUid, String userUid, boolean isDelete) {
        Optional<Wallet> wallet = walletRepo.findByUidAndUserUidAndDeleted(UUID.fromString(walletUid), UUID.fromString(userUid), isDelete);
        if (wallet.isPresent()) {
            WalletDTO walletDTO = new WalletDTO();
            walletDTO.setStatus(HttpStatus.OK.value());
            walletDTO.setMessage(Constants.KEY_SUCESSE);
            walletDTO.setUserId(wallet.get().getUid().toString());
            walletDTO.setValue(wallet.get().getValue());
            walletDTO.setBankAccountName(wallet.get().getBankAccountName());
            walletDTO.setBankAccountNumber(wallet.get().getBankAccountNumber());
            walletDTO.setBankAccountSheba(wallet.get().getBankAccountSheba());
            walletDTO.setUserId(wallet.get().getUser().getUid().toString());
            walletDTO.setOrganizationId(wallet.get().getOrganization().getUid().toString());
            return Optional.ofNullable(walletDTO);
        }
        return Optional.empty();
    }

    public Optional<ListWalletDTO> findAllListWalletDTO(boolean isDelete) {
        Optional<List<Wallet>> orgs = walletRepo.findAllByDeleted(isDelete);
        if (orgs.isPresent()) {
            ListWalletDTO listWalletDTO = new ListWalletDTO();
            List<WalletDTO> dtoList = new ArrayList<>();
            listWalletDTO.setStatus(HttpStatus.OK.value());
            listWalletDTO.setMessage(Constants.KEY_SUCESSE);
            for (Wallet wallet : orgs.get()) {
                WalletDTO walletDTO = new WalletDTO();
                walletDTO.setUserId(wallet.getUid().toString());
                walletDTO.setValue(wallet.getValue());
                walletDTO.setBankAccountName(wallet.getBankAccountName());
                walletDTO.setBankAccountNumber(wallet.getBankAccountNumber());
                walletDTO.setBankAccountSheba(wallet.getBankAccountSheba());
                walletDTO.setUserId(wallet.getUser().getUid().toString());
                walletDTO.setOrganizationId(wallet.getOrganization().getUid().toString());
                dtoList.add(walletDTO);
            }
            listWalletDTO.setData(dtoList);
            return Optional.ofNullable(listWalletDTO);
        }
        return Optional.empty();
    }

}
