package ir.mostashar.model.billwallettransaction.service;

import ir.mostashar.model.billwallettransaction.BillWalletTransaction;
import ir.mostashar.model.billwallettransaction.TransactionType;
import ir.mostashar.model.billwallettransaction.dto.BillWalletTransactionDTO;
import ir.mostashar.model.billwallettransaction.dto.BillWalletTransactionForm;
import ir.mostashar.model.billwallettransaction.dto.ListBillWalletTransactionDTO;
import ir.mostashar.model.billwallettransaction.repository.BillWalletTransactionRepo;
import ir.mostashar.model.request.Request;
import ir.mostashar.model.request.service.RequestService;
import ir.mostashar.model.user.User;
import ir.mostashar.model.user.service.UserServiceImpl;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by mahdi
 * User: mahdi
 * Date: 3/17/19
 * Time: 10:00
 * https://github.com/mahdihp
 */

@Service
public class BillWalletTransactionService {


    @Autowired
    private BillWalletTransactionRepo bwtRepo;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RequestService requestService;

    public void create(BillWalletTransactionForm bwtForm) {
        Optional<User> user = userService.findById(bwtForm.getUserId());
        Optional<Request> request = requestService.findById(bwtForm.getRequestId());
        if (user.isPresent() && request.isPresent()) {
            BillWalletTransaction bwt = new BillWalletTransaction();
            bwt.setUid(UUID.randomUUID());
            bwt.setTransactionNumber(bwtForm.getTransactionNumber());
            bwt.setTrackingNumber(bwtForm.getTrackingNumber());
            bwt.setTransactionDate(System.currentTimeMillis());
            bwt.setBankAccountName(bwtForm.getBankAccountName());
            bwt.setBankAccountNumber(bwtForm.getBankAccountNumber());
            bwt.setBankAccountSheba(bwtForm.getBankAccountSheba());
            bwt.setTransactionType(TransactionType.valueOf(bwtForm.getTransactionType()));
            bwt.setDescription(bwtForm.getDescription());
            bwt.setValue(bwtForm.getValue());
            bwt.setUser(user.get());
            bwt.setRequest(request.get());
            bwtRepo.save(bwt);
        }
    }

    public void update(BillWalletTransactionForm bwtForm) {
        Optional<BillWalletTransaction> bwt = bwtRepo.findByUid(UUID.fromString(bwtForm.getBillWalletTransactionId()));
        if (bwt.isPresent()) {
            bwt.get().setTransactionNumber(bwtForm.getTransactionNumber());
            bwt.get().setTrackingNumber(bwtForm.getTrackingNumber());
            bwt.get().setTransactionDate(System.currentTimeMillis());
            bwt.get().setBankAccountName(bwtForm.getBankAccountName());
            bwt.get().setBankAccountNumber(bwtForm.getBankAccountNumber());
            bwt.get().setBankAccountSheba(bwtForm.getBankAccountSheba());
            bwt.get().setTransactionType(TransactionType.valueOf(bwtForm.getTransactionType()));
            bwt.get().setDescription(bwtForm.getDescription());
            bwt.get().setValue(bwtForm.getValue());
            bwtRepo.save(bwt.get());
        }
    }

    public Optional<BillWalletTransaction> findById(String bwtId) {
        Optional<BillWalletTransaction> bwt = bwtRepo.findByUid(UUID.fromString(bwtId));
        return bwt;
    }

    public Optional<BillWalletTransactionDTO> findDTOById(String bwtId) {
        Optional<BillWalletTransaction> bwt = bwtRepo.findByUid(UUID.fromString(bwtId));
        if (bwt.isPresent()) {
            BillWalletTransactionDTO bwtDTO = new BillWalletTransactionDTO();
            bwtDTO.setStatus(HttpStatus.OK.value());
            bwtDTO.setMessage(Constants.KEY_SUCESSE);

            bwtDTO.setBillWalletTransactionId(bwt.get().getUid().toString());
            bwtDTO.setTransactionNumber(bwt.get().getTransactionNumber());
            bwtDTO.setTrackingNumber(bwt.get().getTrackingNumber());
            bwtDTO.setTransactionDate(bwt.get().getTransactionDate());
            bwtDTO.setBankAccountName(bwt.get().getBankAccountName());
            bwtDTO.setBankAccountNumber(bwt.get().getBankAccountNumber());
            bwtDTO.setBankAccountSheba(bwt.get().getBankAccountSheba());
            bwtDTO.setTransactionType(bwt.get().getTransactionType().type + "");
            bwtDTO.setDescription(bwt.get().getDescription());
            bwtDTO.setValue(bwt.get().getValue());
            bwtDTO.setUserId(bwt.get().getUser().getUid().toString());
            bwtDTO.setRequestId(bwt.get().getRequest().getUid().toString());
            return Optional.ofNullable(bwtDTO);
        }
        return Optional.empty();
    }

    public Optional<ListBillWalletTransactionDTO> findAllDTO(int queryType, String item) {
        Optional<List<BillWalletTransaction>> list = Optional.empty();
        switch (queryType) {
            case 1:
                list = bwtRepo.findAllByRequestUid(UUID.fromString(item));
                break;
            case 2:
                list = bwtRepo.findAllByUserUid(UUID.fromString(item));
                break;
            case 3:
                list = bwtRepo.findAllByTransactionType(TransactionType.valueOf(item));
                break;
        }
        if (list.isPresent()) {
            ListBillWalletTransactionDTO lbwtDTO = new ListBillWalletTransactionDTO();
            lbwtDTO.setStatus(HttpStatus.OK.value());
            lbwtDTO.setMessage(Constants.KEY_SUCESSE);
            List<BillWalletTransactionDTO> dtoList = new ArrayList<>();
            for (BillWalletTransaction bwt : list.get()) {
                BillWalletTransactionDTO bwtDTO = new BillWalletTransactionDTO();
                bwtDTO.setStatus(HttpStatus.OK.value());
                bwtDTO.setMessage(Constants.KEY_SUCESSE);

                bwtDTO.setBillWalletTransactionId(bwt.getUid().toString());
                bwtDTO.setTransactionNumber(bwt.getTransactionNumber());
                bwtDTO.setTrackingNumber(bwt.getTrackingNumber());
                bwtDTO.setTransactionDate(bwt.getTransactionDate());
                bwtDTO.setBankAccountName(bwt.getBankAccountName());
                bwtDTO.setBankAccountNumber(bwt.getBankAccountNumber());
                bwtDTO.setBankAccountSheba(bwt.getBankAccountSheba());
                bwtDTO.setTransactionType(bwt.getTransactionType().type + "");
                bwtDTO.setDescription(bwt.getDescription());
                bwtDTO.setValue(bwt.getValue());
                bwtDTO.setUserId(bwt.getUser().getUid().toString());
                bwtDTO.setRequestId(bwt.getRequest().getUid().toString());
                dtoList.add(bwtDTO);
            }
            lbwtDTO.setData(dtoList);
            return Optional.ofNullable(lbwtDTO);
        }
        return Optional.empty();
    }


}
