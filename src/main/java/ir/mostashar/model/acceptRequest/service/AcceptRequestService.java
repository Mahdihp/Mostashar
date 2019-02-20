package ir.mostashar.model.acceptRequest.service;

import ir.mostashar.model.acceptRequest.AcceptRequest;
import ir.mostashar.model.acceptRequest.dto.AcceptRequestDTO;
import ir.mostashar.model.acceptRequest.dto.AcceptRequestForm;
import ir.mostashar.model.acceptRequest.dto.ListAcceptRequestDTO;
import ir.mostashar.model.acceptRequest.repository.AcceptRequestRepository;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.repository.LawyerRepository;
import ir.mostashar.model.request.Request;
import ir.mostashar.model.request.repository.RequestRepository;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AcceptRequestService {

    @Autowired
    AcceptRequestRepository arRepository;

    @Autowired
    LawyerRepository lawyerRepository;

    @Autowired
    RequestRepository requestRepository;

    public boolean createAcceptRequest(AcceptRequestForm arForm) {
        Optional<Lawyer> lawyer = lawyerRepository.findByUid(UUID.fromString(arForm.getLawyerUid()));
        Optional<Request> request = requestRepository.findRequestByUidAndDeleted(UUID.fromString(arForm.getRequestUid()), false);
        if (lawyer.isPresent() && request.isPresent()) {
            AcceptRequest ar = new AcceptRequest();
            ar.setUid(UUID.randomUUID());
            ar.setAcceptDate(System.currentTimeMillis());
            ar.setFinishedTimeFile(null);
            ar.setVerified(null); // پرسیده شود.
            ar.setLawyer(lawyer.get());
            ar.setRequest(request.get());
            arRepository.save(ar);
            return true;
        }
        return false;
    }

    public boolean deleteAcceptRequest(String uid) {
        Optional<AcceptRequest> acceptRequest = arRepository.findByUid(UUID.fromString(uid));
        if (acceptRequest.isPresent()) {
            arRepository.delete(acceptRequest.get());
            return true;
        }
        return false;
    }

    public Optional<AcceptRequest> findByUid(String uid) {
        Optional<AcceptRequest> acceptRequest = arRepository.findByUid(UUID.fromString(uid));
        if (acceptRequest.isPresent())
            return Optional.ofNullable(acceptRequest.get());
        else
            return Optional.empty();
    }

    public Optional<AcceptRequestDTO> findAcceptRequestDTOByUid(String uid) {
        Optional<AcceptRequest> acceptRequest = arRepository.findByUid(UUID.fromString(uid));
        if (acceptRequest.isPresent()) {
            AcceptRequestDTO arDTO = new AcceptRequestDTO();
            arDTO.setStatus(HttpStatus.OK.value());
            arDTO.setMessage(Constants.KEY_SUCESSE);

            arDTO.setUid(acceptRequest.get().getUid().toString());
            arDTO.setAcceptDate(acceptRequest.get().getAcceptDate());
            arDTO.setFinishedTimeFile(acceptRequest.get().getFinishedTimeFile());
            arDTO.setVerified(acceptRequest.get().getVerified());
            arDTO.setRequestUid(acceptRequest.get().getRequest().getUid().toString());
            arDTO.setLawyerUid(acceptRequest.get().getLawyer().getUid().toString());
            return Optional.ofNullable(arDTO);
        }
        return Optional.empty();
    }

    public Optional<ListAcceptRequestDTO> findListAcceptRequestDTO() {
        List<AcceptRequest> list = arRepository.findAll();
        if (list != null) {
            ListAcceptRequestDTO larDTO = new ListAcceptRequestDTO();
            larDTO.setStatus(HttpStatus.OK.value());
            larDTO.setMessage(Constants.KEY_SUCESSE);
            List<AcceptRequestDTO> dtoList = new ArrayList<>();
            for (AcceptRequest ar : list) {
                AcceptRequestDTO arDTO = new AcceptRequestDTO();
                arDTO.setStatus(HttpStatus.OK.value());
                arDTO.setMessage(Constants.KEY_SUCESSE);

                arDTO.setUid(ar.getUid().toString());
                arDTO.setAcceptDate(ar.getAcceptDate());
                arDTO.setFinishedTimeFile(ar.getFinishedTimeFile());
                arDTO.setVerified(ar.getVerified());
                arDTO.setRequestUid(ar.getRequest().getUid().toString());
                arDTO.setLawyerUid(ar.getLawyer().getUid().toString());
                dtoList.add(arDTO);
            }
            larDTO.setData(dtoList);
            return Optional.ofNullable(larDTO);
        }
        return Optional.empty();
    }

    public Optional<ListAcceptRequestDTO> findListAcceptRequestDTOByLawyer(String lawyerUid) {
        Optional<List<AcceptRequest>> allByLawyerUid = arRepository.findAllByLawyerUid(UUID.fromString(lawyerUid));
        if (allByLawyerUid.isPresent()) {
            ListAcceptRequestDTO larDTO = new ListAcceptRequestDTO();
            larDTO.setStatus(HttpStatus.OK.value());
            larDTO.setMessage(Constants.KEY_SUCESSE);
            List<AcceptRequestDTO> dtoList = new ArrayList<>();
            for (AcceptRequest ar : allByLawyerUid.get()) {
                AcceptRequestDTO arDTO = new AcceptRequestDTO();
                arDTO.setStatus(HttpStatus.OK.value());
                arDTO.setMessage(Constants.KEY_SUCESSE);

                arDTO.setUid(ar.getUid().toString());
                arDTO.setAcceptDate(ar.getAcceptDate());
                arDTO.setFinishedTimeFile(ar.getFinishedTimeFile());
                arDTO.setVerified(ar.getVerified());
                arDTO.setRequestUid(ar.getRequest().getUid().toString());
                arDTO.setLawyerUid(ar.getLawyer().getUid().toString());
                dtoList.add(arDTO);
            }
            larDTO.setData(dtoList);
            return Optional.ofNullable(larDTO);
        }
        return Optional.empty();
    }
}
