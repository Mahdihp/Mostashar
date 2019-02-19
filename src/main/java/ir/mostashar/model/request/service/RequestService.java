package ir.mostashar.model.request.service;

import ir.mostashar.model.adviceType.AdviceType;
import ir.mostashar.model.adviceType.repository.AdviceTypeRepository;
import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.repository.ClientRepository;
import ir.mostashar.model.file.File;
import ir.mostashar.model.file.repository.FileRepository;
import ir.mostashar.model.request.Request;
import ir.mostashar.model.request.RequestStatus;
import ir.mostashar.model.request.dto.ListRequestDTO;
import ir.mostashar.model.request.dto.RequestDTO;
import ir.mostashar.model.request.dto.RequestForm;
import ir.mostashar.model.request.repository.RequestRepository;
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
    RequestRepository requestRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AdviceTypeRepository adviceTypeRepository;

    @Autowired
    FileRepository fileRepository;

    @Value("${mostashar.app.requestNumber}")
    private String requestNumber;

    /**
     * find Advice Type & Client & File by Uids
     * If Not Null this Three Object And Save New Request
     *
     * @param requestForm
     * @return
     */
    public UUID createRequest(RequestForm requestForm) {
        Optional<AdviceType> adviceType = adviceTypeRepository.findAdviceTypeByUid(UUID.fromString(requestForm.getAdviceTypeId()));
        Optional<Client> client = clientRepository.findByUid(UUID.fromString(requestForm.getClientId()));
        Optional<File> file = fileRepository.findFileByUidAndDeleted(UUID.fromString(requestForm.getFileId()), false);
        Long maxRequestNumber = requestRepository.findMaxRequestNumber();
        System.out.println("Log-----------------maxRequestNumber " + maxRequestNumber);
        UUID uuid;
        if (adviceType.isPresent() && client.isPresent() && file.isPresent()) {
            Request request = new Request();
            uuid = UUID.randomUUID();
            request.setUid(uuid);
            request.setStatus(RequestStatus.Waiting);
            if (maxRequestNumber != null) {
                request.setRequestNumber(String.valueOf(maxRequestNumber + 1));
            } else {
                request.setRequestNumber(requestNumber);
            }
            request.setClient(client.get());
            request.setAdvicetype(adviceType.get());
            request.setFile(file.get());
            request.setRequestNumber(request.getRequestNumber());
            requestRepository.save(request);
            return uuid;
        }
        return null;
    }

    public Optional<Request> findByClientIdAndRequestId(String clientid, String requestid) {
        Optional<Request> request = requestRepository.findRequestByClientUidAndUidAndDeleted(UUID.fromString(clientid), UUID.fromString(requestid), false);
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
            requestRepository.save(request);
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
        Optional<Request> request = requestRepository.findRequestByUidAndDeleted(UUID.fromString(requestForm.getRequestId()), false);
        Optional<AdviceType> adviceType = adviceTypeRepository.findAdviceTypeByUid(UUID.fromString(requestForm.getAdviceTypeId()));
        Optional<File> file = fileRepository.findFileByUidAndDeleted(UUID.fromString(requestForm.getFileId()), false);
        if (request.isPresent() && adviceType.isPresent() && file.isPresent()) {

            request.get().setStatus(RequestStatus.valueOf(requestForm.getStatus()));
//            request.get().setAdvicetype(adviceType.get());
//            request.get().setFile(file.get());

            requestRepository.save(request.get());
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
    public Optional<RequestDTO> findRequestByClient(String clientId, String requestId) {
        Optional<Request> request = requestRepository.findRequestByClientUidAndUidAndDeleted(UUID.fromString(clientId), UUID.fromString(requestId), false);
        if (request.isPresent()) {
            RequestDTO requestDTO = new RequestDTO();
            requestDTO.setStatus(HttpStatus.OK.value());
            requestDTO.setMessage(Constants.KEY_SUCESSE);
            requestDTO.setRequestId(request.get().getUid().toString());
            requestDTO.setRequestStatus(request.get().getStatus().name());
            requestDTO.setRequestNumber(request.get().getRequestNumber());

            requestDTO.setClientId(request.get().getClient().getUid().toString());
            requestDTO.setFileId(request.get().getFile().getUid().toString());
            requestDTO.setAdviceTypeId(request.get().getAdvicetype().getUid().toString());

            return Optional.of(requestDTO);
        }
        return Optional.empty();
    }

    public Optional<ListRequestDTO> findAllRequestClient(String clientId) {
        Optional<List<Request>> requestList = requestRepository.findAllByClientUidAndDeleted(UUID.fromString(clientId), false);
        if (requestList.isPresent()) {
            List<RequestDTO> dtoList = new ArrayList<>();
            ListRequestDTO listRequestDTO = new ListRequestDTO();
            for (Request request : requestList.get()) {
                RequestDTO requestDTO = new RequestDTO();
                requestDTO.setRequestId(request.getUid().toString());
                requestDTO.setRequestStatus(request.getStatus().name());
                requestDTO.setRequestNumber(request.getRequestNumber());

                requestDTO.setClientId(request.getClient().getUid().toString());
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

    /**
     * Chcek Duplicate Request in Table
     *
     * @param clientId
     * @param requestId
     * @return true or false
     */
    public boolean existsRequest(String clientId, String requestId) {
        Optional<Boolean> aBoolean = requestRepository.existsRequestByFileUidAndClientUidAndDeleted(UUID.fromString(clientId), UUID.fromString(requestId), false);
        if (aBoolean.isPresent())
            return aBoolean.get();
        else
            return false;
    }


}
