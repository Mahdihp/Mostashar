package ir.mostashar.model.failRequest.service;

import ir.mostashar.model.failRequest.FailRequest;
import ir.mostashar.model.failRequest.dto.FailRequestDTO;
import ir.mostashar.model.failRequest.dto.FailRequestForm;
import ir.mostashar.model.failRequest.dto.ListFailRequestDTO;
import ir.mostashar.model.failRequest.repository.FailRequestRepo;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.repository.LawyerRepo;
import ir.mostashar.model.request.Request;
import ir.mostashar.model.request.repository.RequestRepo;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FailRequestService {

    @Autowired
    FailRequestRepo frRepository;

    @Autowired
    LawyerRepo lawyerRepo;

    @Autowired
    RequestRepo requestRepo;

    public boolean createFailRequest(FailRequestForm frForm) {
        Optional<Lawyer> lawyer = lawyerRepo.findByUid(UUID.fromString(frForm.getLawyerUid()));
        Optional<Request> request = requestRepo.findByUidAndDeleted(UUID.fromString(frForm.getRequestUid()), false);
        if (lawyer.isPresent() && request.isPresent()) {
            FailRequest failRequest = new FailRequest();
            failRequest.setUid(UUID.randomUUID());
            failRequest.setCreationDate(System.currentTimeMillis());
            failRequest.setDescription(frForm.getDescription());
            failRequest.setRequest(request.get());
            failRequest.setLawyer(lawyer.get());
            frRepository.save(failRequest);
            return true;
        }
        return false;
    }

    public boolean deleteFailRequest(String uid) {
        Optional<FailRequest> failRequest = frRepository.findByUid(UUID.fromString(uid));
        if (failRequest.isPresent()) {
            frRepository.delete(failRequest.get());
            return true;
        }
        return false;

    }

    public Optional<FailRequest> findByUid(String uid) {
        Optional<FailRequest> failRequest = frRepository.findByUid(UUID.fromString(uid));
        if (failRequest.isPresent())
            return Optional.ofNullable(failRequest.get());
        else
            return Optional.empty();
    }

    public Optional<FailRequestDTO> findFailRequestDTOByUid(String uid) {
        Optional<FailRequest> failRequest = frRepository.findByUid(UUID.fromString(uid));
        if (failRequest.isPresent()) {
            FailRequestDTO failRequestDTO = new FailRequestDTO();
            failRequestDTO.setStatus(HttpStatus.OK.value());
            failRequestDTO.setMessage(Constants.KEY_SUCESSE);
            failRequestDTO.setUid(failRequest.get().getUid().toString());
            failRequestDTO.setDescription(failRequest.get().getDescription());
            failRequestDTO.setCreationDate(failRequest.get().getCreationDate());
            failRequestDTO.setLawyerUid(failRequest.get().getLawyer().getUid().toString());
            failRequestDTO.setRequestUid(failRequest.get().getRequest().getUid().toString());
            return Optional.ofNullable(failRequestDTO);
        }
        return Optional.empty();
    }

    public Optional<ListFailRequestDTO> findListFailRequestDTO() {
        List<FailRequest> failRequestList = frRepository.findAll();
        if (failRequestList != null) {
            ListFailRequestDTO lfrDTo = new ListFailRequestDTO();
            lfrDTo.setStatus(HttpStatus.OK.value());
            lfrDTo.setMessage(Constants.KEY_SUCESSE);
            List<FailRequestDTO> dtoList = new ArrayList<>();
            for (FailRequest failRequest : failRequestList) {
                FailRequestDTO frDTO = new FailRequestDTO();
                frDTO.setUid(failRequest.getUid().toString());
                frDTO.setDescription(failRequest.getDescription());
                frDTO.setCreationDate(failRequest.getCreationDate());
                frDTO.setLawyerUid(failRequest.getLawyer().getUid().toString());
                frDTO.setRequestUid(failRequest.getRequest().getUid().toString());
                dtoList.add(frDTO);
            }
            lfrDTo.setData(dtoList);
            return Optional.ofNullable(lfrDTo);
        }
        return Optional.empty();
    }
    public Optional<ListFailRequestDTO> findListFailRequestDTOByLawyer(String lawyerUid){
        Optional<List<FailRequest>> allByLawyerUid = frRepository.findAllByLawyerUid(UUID.fromString(lawyerUid));
        if (allByLawyerUid.isPresent()){
            ListFailRequestDTO lfrDTo = new ListFailRequestDTO();
            lfrDTo.setStatus(HttpStatus.OK.value());
            lfrDTo.setMessage(Constants.KEY_SUCESSE);
            List<FailRequestDTO> dtoList = new ArrayList<>();
            for (FailRequest failRequest : allByLawyerUid.get()) {
                FailRequestDTO frDTO = new FailRequestDTO();
                frDTO.setUid(failRequest.getUid().toString());
                frDTO.setDescription(failRequest.getDescription());
                frDTO.setCreationDate(failRequest.getCreationDate());
                frDTO.setLawyerUid(failRequest.getLawyer().getUid().toString());
                frDTO.setRequestUid(failRequest.getRequest().getUid().toString());
                dtoList.add(frDTO);
            }
            lfrDTo.setData(dtoList);
            return Optional.ofNullable(lfrDTo);
        }
        return Optional.empty();
    }
}
