package ir.mostashar.model.request.service;

import ir.mostashar.model.adviceType.AdviceType;
import ir.mostashar.model.adviceType.repository.AdviceTypeRepo;
import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.service.ClientService;
import ir.mostashar.model.file.File;
import ir.mostashar.model.file.repository.FileRepo;
import ir.mostashar.model.notification.service.NotificationService;
import ir.mostashar.model.request.Request;
import ir.mostashar.model.request.RequestStatus;
import ir.mostashar.model.request.dto.ListRequestDTO;
import ir.mostashar.model.request.dto.RequestDTO;
import ir.mostashar.model.request.dto.RequestForm;
import ir.mostashar.model.request.repository.RequestRepo;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RequestService {

    @Autowired
    private RequestRepo requestRepo;
    @Autowired
    private ClientService clientService;
    @Autowired
    private AdviceTypeRepo adviceTypeRepo;
    @Autowired
    private FileRepo fileRepo;
    @Autowired
    private NotificationService notificationService;
    @Value("${mostashar.app.requestNumber}")
    private long requestNumber;

    /**
     * find Advice Type & Client & File by Uids
     * If Not Null this Three Object And Save New Request
     *
     * @param requestForm
     * @return
     */
    public UUID create(RequestForm requestForm) {

        Optional<AdviceType> adviceType = adviceTypeRepo.findByUid(UUID.fromString(requestForm.getAdviceTypeId()));
        Optional<Client> client = clientService.findClientByUidAndActive(requestForm.getUserId(), true);
        Optional<File> file = fileRepo.findByUidAndDeleted(UUID.fromString(requestForm.getFileId()), false);

        Long maxRequestNumber = requestRepo.findMaxRequestNumber();

        System.out.println("Log-----------------maxRequestNumber " + maxRequestNumber);

        UUID uuid;
        if (adviceType.isPresent() && client.isPresent() && file.isPresent()) {
            Request request = new Request();
            uuid = UUID.randomUUID();
            request.setUid(uuid);
            if (maxRequestNumber != null) {
                request.setRequestNumber(maxRequestNumber + 1);
            } else {
                request.setRequestNumber(requestNumber);
            }
            request.setClient(client.get());
            request.setRequestStatus(RequestStatus.SELECT_LAWYER);
            request.setCreationDate(System.currentTimeMillis());
            request.setAdvicetype(adviceType.get());
            request.setFile(file.get());
            requestRepo.save(request);
            return uuid;
        }
        return null;
    }

    public boolean update(String uid, RequestStatus requestStatus) {
        Optional<Request> request = requestRepo.findByUidAndDeleted(UUID.fromString(uid), false);
        if (request.isPresent()) {
            request.get().setRequestStatus(requestStatus);
            requestRepo.save(request.get());
            return true;
        }
        return false;
    }

    public Optional<Request> findByFileId(String fileUid) {
        Optional<Request> request = requestRepo.findByFileUidAndDeleted(UUID.fromString(fileUid), false);
        if (request.isPresent())
            return request;
        else
            return Optional.empty();
    }


    public Optional<Request> findById(String requestUid) {
        Optional<Request> request = requestRepo.findByUidAndDeleted(UUID.fromString(requestUid), false);
        if (request.isPresent())
            return request;
        else
            return Optional.empty();
    }

    /**
     * Logic Delete Record
     *
     * @param request
     * @return true & false
     */
    public boolean deleteRequest(Request request) {
//        Optional<Request> request = requestRepository.findRequestByClientUidAndUidAndDeleted(UUID.fromString(clientId), UUID.fromString(requestId), false);
        if (request != null) {
            request.setDeleted(true);
            requestRepo.save(request);
            return true;
        }
        return false;
    }

    /**
     * find Advice Type & Client & File by Uids
     * If Not Null this Three Object And Update Request
     *
     * @param requestForm
     * @return
     */
    public boolean updateRequest(RequestForm requestForm) {
        Optional<Request> request = requestRepo.findByUidAndDeleted(UUID.fromString(requestForm.getRequestId()), false);
        Optional<AdviceType> adviceType = adviceTypeRepo.findByUid(UUID.fromString(requestForm.getAdviceTypeId()));
        Optional<File> file = fileRepo.findByUidAndDeleted(UUID.fromString(requestForm.getFileId()), false);
        if (request.isPresent() && adviceType.isPresent() && file.isPresent()) {

//            request.get().setStatus(RequestStatus.valueOf(requestForm.getStatus()));
//            request.get().setAdvicetype(adviceType.get());
//            request.get().setFile(file.get());

            requestRepo.save(request.get());
            return true;
        }
        return false;
    }

    /**
     * Find & Check Not Null ClientId & RequestId Object
     * And Create Object From RequestDTO
     *
     * @param clientId
     * @param requestId
     * @return RequestDTO
     */
    public Optional<RequestDTO> findByClientIdAndRequestId(String clientId, String requestId) {
        Optional<Request> request = requestRepo.findByUidAndClientUidAndDeleted(UUID.fromString(requestId), UUID.fromString(clientId), false);
        if (request.isPresent()) {
            RequestDTO requestDTO = new RequestDTO();
            requestDTO.setStatus(HttpStatus.OK.value());
            requestDTO.setMessage(Constants.KEY_SUCESSE);
            requestDTO.setRequestId(request.get().getUid().toString());
            requestDTO.setRequestStatus(request.get().getRequestStatus().name());
            requestDTO.setRequestNumber(request.get().getRequestNumber());
            requestDTO.setFileTitle(request.get().getFile().getTitle());
            requestDTO.setAdviceTitle(request.get().getAdvicetype().getName());
//            requestDTO.setClientId(request.get().getClient().getUid().toString());
            requestDTO.setFileId(request.get().getFile().getUid().toString());
            requestDTO.setAdviceTypeId(request.get().getAdvicetype().getUid().toString());

            return Optional.of(requestDTO);
        }
        return Optional.empty();
    }

    public Optional<ListRequestDTO> findAllRequestClient(String clientId) {
        Optional<List<Request>> requestList = requestRepo.findAllByClientUidAndDeleted(UUID.fromString(clientId), false);
        if (requestList.isPresent()) {
            List<RequestDTO> dtoList = new ArrayList<>();
            ListRequestDTO listRequestDTO = new ListRequestDTO();
            for (Request request : requestList.get()) {
                RequestDTO requestDTO = new RequestDTO();
                requestDTO.setRequestId(request.getUid().toString());
                requestDTO.setRequestStatus(request.getRequestStatus().name());
                requestDTO.setRequestNumber(request.getRequestNumber());
                requestDTO.setFileTitle(request.getFile().getTitle());
                requestDTO.setAdviceTitle(request.getAdvicetype().getName());

//                requestDTO.setClientId(request.getClient().getUid().toString());
                requestDTO.setFileId(request.getFile().getUid().toString());
                requestDTO.setAdviceTypeId(request.getAdvicetype().getUid().toString());
                dtoList.add(requestDTO);
            }

            listRequestDTO.setStatus(HttpStatus.OK.value());
            listRequestDTO.setMessage(Constants.KEY_SUCESSE);
            listRequestDTO.setRequests(dtoList);
            return Optional.of(listRequestDTO);
        }
        return Optional.empty();
    }

    public boolean existsRequest(String requestId) {
        Optional<Boolean> exists = requestRepo.existsByUid(UUID.fromString(requestId));
        if (exists.isPresent())
            return exists.get();
        else
            return false;
    }

    /*public boolean existsRequest(String lawyerId, String fileUid) {
        Optional<Boolean> aBoolean = requestRepo
                .existsRequestByFileUidAndClientUidAndDeleted(UUID.fromString(lawyerId), UUID.fromString(fileUid), false);
        if (aBoolean.isPresent())
            return aBoolean.get();
        else
            return false;
    }*/

}
