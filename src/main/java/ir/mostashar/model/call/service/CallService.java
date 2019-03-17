package ir.mostashar.model.call.service;

import ir.mostashar.model.call.Call;
import ir.mostashar.model.call.dto.CallDTO;
import ir.mostashar.model.call.dto.CallForm;
import ir.mostashar.model.call.dto.ListCallDTO;
import ir.mostashar.model.call.repository.CallRepo;
import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.service.ClientService;
import ir.mostashar.model.expertise.dto.ExpertiseDTO;
import ir.mostashar.model.lawyer.Lawyer;
import ir.mostashar.model.lawyer.dto.LawyerDTO;
import ir.mostashar.model.lawyer.dto.ListLawyerDTO;
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

    public boolean create(CallForm callForm) {
        Optional<Client> client = clientService.findClientByUidAndActive(callForm.getClientId(), false);
        Optional<Lawyer> lawyer = lawyerService.findLawyerUidAndActive(callForm.getLawyerId(), true);
        Optional<Request> request = requestService.findById(callForm.getRequestId());
        if (client.isPresent() && request.isPresent() && lawyer.isPresent()) {
            Call call = new Call();
            call.setUid(UUID.randomUUID());
            call.setFailedRetriesCount(callForm.getFailedRetriesCount());
//            call.setCallStatus(callForm.getCallStatus());
            call.setCallType(callForm.getCallType());
            call.setStartTime(callForm.getStartTime());
            call.setEndTime(callForm.getEndTime());
            call.setCreationDate(System.currentTimeMillis());
            call.setClient(client.get());
            call.setRequest(request.get());
            call.setLawyer(lawyer.get());
            callRepo.save(call);
            return false;
        }
        return false;
    }

    public Optional<ListLawyerDTO> findAllLawyerById(String clientId) {
        Optional<List<Call>> list = callRepo.findAllByClientUid(UUID.fromString(clientId));
        if (list.isPresent()) {
            List<Lawyer> lawyerList = new ArrayList<>();
            for (Call call : list.get()) {
                lawyerList.add(call.getLawyer());
            }
            if (lawyerList != null && lawyerList.size() > 0) {
                ListLawyerDTO llDTO = new ListLawyerDTO();
                llDTO.setStatus(HttpStatus.OK.value());
                llDTO.setMessage(Constants.KEY_SUCESSE);

                List<LawyerDTO> dtoList = new ArrayList<>();
                for (Lawyer lawyer : lawyerList) {
                    LawyerDTO lawyerDTO = new LawyerDTO();
                    lawyerDTO.setLawyerId(lawyer.getUid().toString());
                    lawyerDTO.setFirstName(lawyer.getFirstName());
                    lawyerDTO.setLastName(lawyer.getLastName());
                    lawyerDTO.setUsername(lawyer.getUsername());
                    lawyerDTO.setPassword(lawyer.getPassword());
                    lawyerDTO.setNationalId(lawyer.getNationalId());
                    lawyerDTO.setBirthDate(lawyer.getBirthDate());
                    lawyerDTO.setOnline(lawyer.getOnline());
                    lawyerDTO.setScore(lawyer.getScore());
                    lawyerDTO.setAvatarHashcode(lawyer.getAvatarHashcode());
                    lawyerDTO.setActive(lawyer.getActive());
                    lawyerDTO.setMobileNumber(lawyer.getMobileNumber());
//            lawyerDTO.setVerificationCode(lawyer.getVerificationCode());
                    lawyerDTO.setCreationDate(lawyer.getCreationDate());
//            lawyerDTO.setModificationDate(lawyer.getModificationDate());
                    lawyerDTO.setAvailable(lawyer.getAvailable());
                    lawyerDTO.setLevel(lawyer.getLevel());
                    lawyerDTO.setPricePerMinute(lawyer.getPricePerMinute());
                    lawyerDTO.setVerified(lawyer.getVerified());
                    List<ExpertiseDTO> expertelist = new ArrayList<>();
                    lawyer.getExpertises().stream()
                            .forEach(ex -> expertelist.add(new ExpertiseDTO(ex.getUid().toString(), ex.getName(), ex.getDescription())));
                    lawyerDTO.setExpertiseList(expertelist);
                    lawyerDTO.setOrganizationId(lawyer.getNationalId());
                    lawyerDTO.setAdvicetypeId(lawyer.getAdvicetype().getUid().toString());
                    dtoList.add(lawyerDTO);
                }
                llDTO.setData(dtoList);
                return Optional.ofNullable(llDTO);
            }
        }
        return Optional.empty();
    }

    public Optional<List<Call>> findAllCallByLawyerId(String lawyerId) {
        Optional<List<Call>> list = callRepo.findAllByLawyerUid(UUID.fromString(lawyerId));
        if (list.isPresent())
            return Optional.ofNullable(list.get());
        else
            return Optional.empty();
    }

    /**
     * First Uid Type Client or Lawyer or Request
     * and later find from callId set
     *
     * @param uid
     * @param queryType
     * @return
     */
    public Optional<ListCallDTO> findListCallDTOByUid(int queryType, String uid) {
        Optional<List<Call>> list = Optional.empty();
        switch (queryType) {
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
                callDTO.setCallId(call.getUid().toString());
                callDTO.setFailedRetriesCount(call.getFailedRetriesCount());
//                callDTO.setCallStatus(call.getCallStatus());
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
