package ir.mostashar.model.feedback.service;

import ir.mostashar.model.client.service.ClientService;
import ir.mostashar.model.feedback.FeedBack;
import ir.mostashar.model.feedback.dto.FeedBackDTO;
import ir.mostashar.model.feedback.dto.FeedBackForm;
import ir.mostashar.model.feedback.dto.ListFeedBackDTO;
import ir.mostashar.model.feedback.repository.FeedbackRepo;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.service.LawyerService;
import ir.mostashar.model.request.Request;
import ir.mostashar.model.request.service.RequestService;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FeedbackService {

    @Autowired
    FeedbackRepo feedbackRepo;

    @Autowired
    RequestService requestService;

    @Autowired
    LawyerService lawyerService;

    public void createFeedback(FeedBackForm fbForm) {
        Optional<Lawyer> lawyer = lawyerService.findByUid(fbForm.getLawyerId());
        Optional<Request> request = requestService.findByUid(fbForm.getRequestId());
        if (lawyer.isPresent() && request.isPresent()) {
            FeedBack feedBack = new FeedBack();
            feedBack.setUid(UUID.randomUUID());
            feedBack.setCreationDate(System.currentTimeMillis());
            feedBack.setLawyer(lawyer.get());
            feedBack.setRequest(request.get());
            feedBack.setDescription(fbForm.getDescription());
            feedBack.setScore(fbForm.getScore());
            feedbackRepo.save(feedBack);
        }
    }

    public Optional<FeedBack> findByUid(String uid) {
        Optional<FeedBack> feedBack = feedbackRepo.findByUid(UUID.fromString(uid));
        if (feedBack.isPresent())
            return Optional.ofNullable(feedBack.get());
        else
            return Optional.empty();
    }

    public Optional<FeedBackDTO> findFeedBackDTOByUid(String uid) {
        Optional<FeedBack> feedBack = feedbackRepo.findByUid(UUID.fromString(uid));
        if (feedBack.isPresent()) {
            FeedBackDTO feedBackDTO = new FeedBackDTO();
            feedBackDTO.setStatus(HttpStatus.OK.value());
            feedBackDTO.setMessage(Constants.KEY_SUCESSE);
            feedBackDTO.setFeedBackId(feedBack.get().getUid().toString());
            feedBackDTO.setCreationDate(feedBack.get().getCreationDate());
            feedBackDTO.setDescription(feedBack.get().getDescription());
            feedBackDTO.setScore(feedBack.get().getScore());
            feedBackDTO.setLawyerId(feedBack.get().getLawyer().getUid().toString());
            feedBackDTO.setRequestId(feedBack.get().getRequest().getUid().toString());
            return Optional.ofNullable(feedBackDTO);
        }
        return Optional.empty();
    }

    public Optional<ListFeedBackDTO> findByLawyerUidOrRequestUid(int typeQuery, String clientUid_requestUid) {
        Optional<List<FeedBack>> list = Optional.empty();
        switch (typeQuery) {
            case 1:
                list = feedbackRepo.findByLawyerUid(UUID.fromString(clientUid_requestUid));
                break;
            case 2:
                list = feedbackRepo.findByRequestUid(UUID.fromString(clientUid_requestUid));
        }
        if (list.isPresent()) {
            ListFeedBackDTO lfbDTO = new ListFeedBackDTO();
            lfbDTO.setStatus(HttpStatus.OK.value());
            lfbDTO.setMessage(Constants.KEY_SUCESSE);
            List<FeedBackDTO> dtoList = new ArrayList<>();
            for (FeedBack feedBack : list.get()) {
                FeedBackDTO feedBackDTO = new FeedBackDTO();
                feedBackDTO.setFeedBackId(feedBack.getUid().toString());
                feedBackDTO.setCreationDate(feedBack.getCreationDate());
                feedBackDTO.setDescription(feedBack.getDescription());
                feedBackDTO.setScore(feedBack.getScore());
                feedBackDTO.setLawyerId(feedBack.getLawyer().getUid().toString());
                feedBackDTO.setRequestId(feedBack.getRequest().getUid().toString());
                dtoList.add(feedBackDTO);
            }
            lfbDTO.setData(dtoList);
            return Optional.ofNullable(lfbDTO);
        }
        return Optional.empty();
    }

    public Optional<ListFeedBackDTO> findByLawyerUidAndRequestUid(String clientUid,String requestUid) {
        Optional<List<FeedBack>> list = feedbackRepo.findByLawyerUidAndByRequestID(UUID.fromString(clientUid),
                UUID.fromString(requestUid));
        if (list.isPresent()) {
            ListFeedBackDTO lfbDTO = new ListFeedBackDTO();
            lfbDTO.setStatus(HttpStatus.OK.value());
            lfbDTO.setMessage(Constants.KEY_SUCESSE);
            List<FeedBackDTO> dtoList = new ArrayList<>();
            for (FeedBack feedBack : list.get()) {
                FeedBackDTO feedBackDTO = new FeedBackDTO();
                feedBackDTO.setFeedBackId(feedBack.getUid().toString());
                feedBackDTO.setCreationDate(feedBack.getCreationDate());
                feedBackDTO.setDescription(feedBack.getDescription());
                feedBackDTO.setScore(feedBack.getScore());
                feedBackDTO.setLawyerId(feedBack.getLawyer().getUid().toString());
                feedBackDTO.setRequestId(feedBack.getRequest().getUid().toString());
                dtoList.add(feedBackDTO);
            }
            lfbDTO.setData(dtoList);
            return Optional.ofNullable(lfbDTO);
        }
        return Optional.empty();
    }


}
