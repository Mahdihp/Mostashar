package ir.mostashar.model.calls.service;

import ir.mostashar.model.calls.dto.CallForm;
import ir.mostashar.model.calls.repository.CallRepo;
import ir.mostashar.model.client.service.ClientService;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.service.LawyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallService {

    @Autowired
    CallRepo callRepo;

    @Autowired
    ClientService clientService;

    @Autowired
    LawyerService lawyerService;

    public boolean createCall(CallForm callForm){

        return false;
    }

}
