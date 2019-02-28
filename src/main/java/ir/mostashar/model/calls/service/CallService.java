package ir.mostashar.model.calls.service;

import ir.mostashar.model.calls.Call;
import ir.mostashar.model.calls.dto.CallDTO;
import ir.mostashar.model.calls.dto.CallForm;
import ir.mostashar.model.calls.dto.ListCallDTO;
import ir.mostashar.model.calls.repository.CallRepo;
import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.service.ClientService;
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
public class CallService {

    @Autowired
    private CallRepo callRepo;

    @Autowired
    private ClientService clientService;

    @Autowired
    private LawyerService lawyerService;

    @Autowired
    private RequestService requestService;

    public boolean createCall(CallForm callForm) {
        Optional<Client> client = clientService.findClientByUidAndActive(callForm.getClientUid(), false);
//        lawyer = lawyerService.
        Optional<Request> request = requestService.findByUid(callForm.getRequestUid());
        if (client.isPresent() && request.isPresent()) {
            Call call = new Call();
            call.setUid(UUID.randomUUID());
//            call.setFailedRetriesCount(callForm.getFailedRetriesCount());
            call.setCallStatus(callForm.getCallStatus());
            call.setCallType(callForm.getCallType());
            call.setStartTime(callForm.getStartTime());
            call.setEndTime(callForm.getEndTime());
//            call.setCreationDate(System.currentTimeMillis());
            call.setClient(client.get());
            call.setRequest(request.get());
            // wait for lawyer service
            return false;
        }
        return false;
    }

    /**
     * First Uid Type Client or Lawyer or Request
     * and later find from uid set
     *
     * @param uid
     * @param uidType
     * @return
     */
    public Optional<ListCallDTO> findListCallDTOByUid(String uid, int uidType) {
        Optional<List<Call>> list = Optional.empty();
        switch (uidType) {
            case 1:
                list = callRepo.findAllByClientUid(UUID.fromString(uid));
                break;
            case 2:
                list = callRepo.findAllByLawyerUid(UUID.fromString(uid));
                break;
            case 3:
                list = callRepo.findAllByRequestUid(UUID.fromString(uid));
                break;
        }
        if (list.isPresent()) {
            ListCallDTO lcDTO = new ListCallDTO();
            lcDTO.setStatus(HttpStatus.OK.value());
            lcDTO.setMessage(Constants.KEY_SUCESSE);
            List<CallDTO> dtoList = new ArrayList<>();
            for (Call call : list.get()) {
                CallDTO callDTO = new CallDTO();
                callDTO.setId(call.getUid().toString());
//                callDTO.setFailedRetriesCount(call.getFailedRetriesCount());
                callDTO.setCallStatus(call.getCallStatus());
                callDTO.setCallType(call.getCallType());
                callDTO.setStartTime(call.getStartTime());
                callDTO.setEndTime(call.getEndTime());
                callDTO.setCreationDate(System.currentTimeMillis());
                callDTO.setClientId(call.getClient().getUid().toString());
                callDTO.setLawyerId(call.getLawyer().getUid().toString());
                callDTO.setRequestId(call.getRequest().getUid().toString());
                dtoList.add(callDTO);
            }
            lcDTO.setData(dtoList);
            return Optional.ofNullable(lcDTO);
        }
        return Optional.empty();
    }

}
