package ir.mostashar.model.billwallettransaction.service;

import ir.mostashar.model.billwallettransaction.BillWalletTransaction;
import ir.mostashar.model.billwallettransaction.TransactionType;
import ir.mostashar.model.billwallettransaction.dto.BillWalletTransactionForm;
import ir.mostashar.model.billwallettransaction.repository.BillWalletTransactionRepo;
import ir.mostashar.model.request.Request;
import ir.mostashar.model.request.service.RequestService;
import ir.mostashar.model.user.User;
import ir.mostashar.model.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class BillWalletTransactionservice {


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


        }
    }
}
