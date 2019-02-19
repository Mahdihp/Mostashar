package ir.mostashar.model.failRequest.service;

import ir.mostashar.model.failRequest.FailRequest;
import ir.mostashar.model.failRequest.dto.FailRequestForm;
import ir.mostashar.model.failRequest.repository.FailRequestRepository;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.repository.LawyerRepository;
import ir.mostashar.model.request.Request;
import ir.mostashar.model.request.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FailRequestService {

    @Autowired
    FailRequestRepository frRepository;

    @Autowired
    LawyerRepository lawyerRepository;

    @Autowired
    RequestRepository requestRepository;

    public boolean createFailRequest(FailRequestForm frForm){
        Optional<Lawyer> lawyer = lawyerRepository.findByUid(UUID.fromString(frForm.getLawyerUid()));
        Optional<Request> request = requestRepository.findRequestByUidAndDeleted(UUID.fromString(frForm.getRequestUid()), false);
        if (lawyer.isPresent() && request.isPresent()) {
            FailRequest failRequest=new FailRequest();
        }
        return false;
    }

}
