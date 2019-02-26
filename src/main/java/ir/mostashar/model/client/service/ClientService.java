package ir.mostashar.model.client.service;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.dto.ClientDTO;
import ir.mostashar.model.client.dto.ListClientDTO;
import ir.mostashar.model.client.repository.ClientRepo;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {


    @Autowired
    private ClientRepo clientRepo;


    public Optional<Client> findClientByUidAndActive(String userid,boolean active) {
        Optional<Client> client = clientRepo.findClientByUidAndActive(UUID.fromString(userid),active);
        if (client.isPresent())
            return Optional.ofNullable(client.get());
        else
            return Optional.empty();
    }

    public Optional<ListClientDTO> findAllListClientDTO() {
        List<Client> clients = clientRepo.findAll();
        if (clients != null) {
            ListClientDTO lbcDTO = new ListClientDTO();
            lbcDTO.setStatus(HttpStatus.OK.value());
            lbcDTO.setMessage(Constants.KEY_SUCESSE);
            List<ClientDTO> dtoList = new ArrayList<>();
            for (Client client : clients) {
                ClientDTO bcDTO = new ClientDTO();
                bcDTO.setUid(client.getUid().toString());
                bcDTO.setFirstName(client.getFirstName());
                bcDTO.setLastName(client.getLastName());
                bcDTO.setFatherName(client.getFatherName());
                bcDTO.setUsername(client.getUsername());
                bcDTO.setPassword(client.getPassword());
                bcDTO.setNationalId(client.getNationalId());
                bcDTO.setBirthDate(client.getBirthDate());
                bcDTO.setIsOnline(client.getOnline());
                bcDTO.setScore(client.getScore());
                bcDTO.setAvatarHashcode(client.getAvatarHashcode());
                bcDTO.setIsActive(client.getActive());
                bcDTO.setMobileNumber(client.getMobileNumber());
                bcDTO.setCreationDate(client.getCreationDate());
                bcDTO.setModificationDate(client.getModificationDate());
                bcDTO.setRoleName(client.getRoles().toString());
                bcDTO.setJobTitle(client.getJobTitle());
                bcDTO.setAddress(client.getAddress());
                bcDTO.setPostalCode(client.getPostalCode());
                bcDTO.setFieldOfStudy(client.getFieldOfStudy());
                bcDTO.setTel(client.getTel());
//            bcDTO.setVerificationCode(client.getVerificationCode());
                dtoList.add(bcDTO);
            }
            lbcDTO.setData(dtoList);
            return Optional.ofNullable(lbcDTO);
        }
        return Optional.empty();
    }

    public Optional<ClientDTO> findClientDTOByUid(String userid) {
        Optional<Client> client = clientRepo.findClientByUidAndActive(UUID.fromString(userid),true);
        if (client.isPresent()) {
            ClientDTO bcDTO = new ClientDTO();
            bcDTO.setStatus(HttpStatus.OK.value());
            bcDTO.setMessage(Constants.KEY_SUCESSE);

            bcDTO.setUid(client.get().getUid().toString());
            bcDTO.setFirstName(client.get().getFirstName());
            bcDTO.setLastName(client.get().getLastName());
            bcDTO.setFatherName(client.get().getFatherName());
            bcDTO.setUsername(client.get().getUsername());
            bcDTO.setPassword(client.get().getPassword());
            bcDTO.setNationalId(client.get().getNationalId());
            bcDTO.setBirthDate(client.get().getBirthDate());
            bcDTO.setIsOnline(client.get().getOnline());
            bcDTO.setScore(client.get().getScore());
            bcDTO.setAvatarHashcode(client.get().getAvatarHashcode());
            bcDTO.setIsActive(client.get().getActive());
            bcDTO.setMobileNumber(client.get().getMobileNumber());
            bcDTO.setCreationDate(client.get().getCreationDate());
            bcDTO.setModificationDate(client.get().getModificationDate());
            bcDTO.setRoleName(client.get().getRoles().toString());
            bcDTO.setJobTitle(client.get().getJobTitle());
            bcDTO.setAddress(client.get().getAddress());
            bcDTO.setPostalCode(client.get().getPostalCode());
            bcDTO.setFieldOfStudy(client.get().getFieldOfStudy());
            bcDTO.setTel(client.get().getTel());
//            bcDTO.setVerificationCode(client.get().getVerificationCode());
            return Optional.ofNullable(bcDTO);
        }
        return Optional.empty();
    }
}
