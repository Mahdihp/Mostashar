package ir.mostashar.model.acceptRequest.service;

import ir.mostashar.model.acceptRequest.AcceptRequest;
import ir.mostashar.model.acceptRequest.dto.AcceptRequestDTO;
import ir.mostashar.model.acceptRequest.dto.AcceptRequestForm;
import ir.mostashar.model.acceptRequest.dto.ListAcceptRequestDTO;
import ir.mostashar.model.acceptRequest.repository.AcceptRequestRepo;
import ir.mostashar.model.file.File;
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
public class AcceptRequestService {

    @Autowired
    AcceptRequestRepo arRepository;

    @Autowired
    LawyerRepo lawyerRepo;

    @Autowired
    RequestRepo requestRepo;

    public boolean createAcceptRequest(AcceptRequestForm arForm) {
        Optional<Lawyer> lawyer = lawyerRepo.findByUid(UUID.fromString(arForm.getLawyerId()));
        Optional<Request> request = requestRepo.findByUidAndDeleted(UUID.fromString(arForm.getRequestId()), false);
        if (lawyer.isPresent() && request.isPresent()) {
            AcceptRequest ar = new AcceptRequest();
            ar.setUid(UUID.randomUUID());
            ar.setCreationDate(System.currentTimeMillis());
            ar.setFinishedTimeFile(null);
            ar.setVerified(null); // پرسیده شود.
            ar.setLawyer(lawyer.get());
            ar.setRequest(request.get());
            arRepository.save(ar);
            return true;
        }
        return false;
    }

    public boolean assignLawyerToRequest(String lawyerUid, String requestUid,boolean isAssign) {
        Optional<AcceptRequest> acceptRequest = arRepository.findByRequestUidAndLawyerUid(UUID.fromString(lawyerUid), UUID.fromString(requestUid));
        if (acceptRequest.isPresent()) {
            acceptRequest.get().setAcceptedByClient(isAssign);
            arRepository.save(acceptRequest.get());
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

            arDTO.setAcceptRequesId(acceptRequest.get().getUid().toString());
            arDTO.setCreationDate(acceptRequest.get().getCreationDate());
            arDTO.setFinishedTimeFile(acceptRequest.get().getFinishedTimeFile());
            arDTO.setVerified(acceptRequest.get().getVerified());
            arDTO.setRequestId(acceptRequest.get().getRequest().getUid().toString());
            arDTO.setLawyerId(acceptRequest.get().getLawyer().getUid().toString());
            return Optional.ofNullable(arDTO);
        }
        return Optional.empty();
    }


    public Optional<ListAcceptRequestDTO> findListAcceptRequestDTO(String requestUid,String fileUid) {
        Optional<List<AcceptRequest>> list = arRepository.findAllByRequestUid(UUID.fromString(requestUid));
        if (list.isPresent()) {
            if (list.get().size() > 0) {
                File file = list.get().get(0).getRequest().getFile();
                if (!file.getUid().toString().equals(fileUid))
                    return Optional.empty();
            }
            ListAcceptRequestDTO larDTO = new ListAcceptRequestDTO();
            larDTO.setStatus(HttpStatus.OK.value());
            larDTO.setMessage(Constants.KEY_SUCESSE);
            List<AcceptRequestDTO> dtoList = new ArrayList<>();
            for (AcceptRequest ar : list.get()) {
                AcceptRequestDTO arDTO = new AcceptRequestDTO();
                arDTO.setStatus(HttpStatus.OK.value());
                arDTO.setMessage(Constants.KEY_SUCESSE);

                arDTO.setAcceptRequesId(ar.getUid().toString());
                arDTO.setCreationDate(ar.getCreationDate());
                arDTO.setFinishedTimeFile(ar.getFinishedTimeFile());
                arDTO.setVerified(ar.getVerified());
                arDTO.setRequestId(ar.getRequest().getUid().toString());
                arDTO.setLawyerId(ar.getLawyer().getUid().toString());
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

                arDTO.setAcceptRequesId(ar.getUid().toString());
                arDTO.setCreationDate(ar.getCreationDate());
                arDTO.setFinishedTimeFile(ar.getFinishedTimeFile());
                arDTO.setVerified(ar.getVerified());
                arDTO.setRequestId(ar.getRequest().getUid().toString());
                arDTO.setLawyerId(ar.getLawyer().getUid().toString());
                dtoList.add(arDTO);
            }
            larDTO.setData(dtoList);
            return Optional.ofNullable(larDTO);
        }
        return Optional.empty();
    }
}
