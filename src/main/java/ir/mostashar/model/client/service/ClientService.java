package ir.mostashar.model.client.service;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.dto.ClientDTO;
import ir.mostashar.model.client.dto.ClientProfileForm;
import ir.mostashar.model.client.dto.ListClientDTO;
import ir.mostashar.model.client.repository.ClientRepo;
import ir.mostashar.model.user.User;
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


    public Optional<Client> findClientByUidAndActive(String userid, boolean active) {
        Optional<Client> client = clientRepo.findClientByUidAndActive(UUID.fromString(userid), active);
        if (client.isPresent())
            return Optional.ofNullable(client.get());
        else
            return Optional.empty();
    }

    public boolean updateClient(ClientProfileForm cpForm) {
        Optional<Client> client = clientRepo.findByUid(UUID.fromString(cpForm.getUserId()));
        if (client.isPresent()) {
            client.get().setFirstName(cpForm.getFirstName());
            client.get().setLastName(cpForm.getLastName());
            client.get().setFatherName(cpForm.getFatherName());
            client.get().setNationalId(cpForm.getNationalId());
            client.get().setBirthDate(cpForm.getBirthDate());
            client.get().setAvatarHashcode(cpForm.getAvatarHashcode());
            client.get().setActive(cpForm.getActive());
            client.get().setMobileNumber(cpForm.getMobileNumber());
            client.get().setJobTitle(cpForm.getJobTitle());
            client.get().setAddress(cpForm.getAddress());
            client.get().setPostalCode(cpForm.getPostalCode());
            client.get().setFieldOfStudy(cpForm.getFieldOfStudy());
            client.get().setTel(cpForm.getTel());
            clientRepo.save(client.get());
        }
        return false;
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
                bcDTO.setUserId(client.getUid().toString());
                bcDTO.setFirstName(client.getFirstName());
                bcDTO.setLastName(client.getLastName());
                bcDTO.setFatherName(client.getFatherName());
                bcDTO.setUsername(client.getUsername());
                bcDTO.setPassword(client.getPassword());
                bcDTO.setNationalId(client.getNationalId());
                bcDTO.setBirthDate(client.getBirthDate());
                bcDTO.setScore(client.getScore());
                bcDTO.setAvatarHashcode(client.getAvatarHashcode());
                bcDTO.setActive(client.getActive());
                bcDTO.setMobileNumber(client.getMobileNumber());
                bcDTO.setJobTitle(client.getJobTitle());
                bcDTO.setAddress(client.getAddress());
                bcDTO.setPostalCode(client.getPostalCode());
                bcDTO.setFieldOfStudy(client.getFieldOfStudy());
                bcDTO.setTel(client.getTel());
                bcDTO.setWalletId(client.getWallet().getUid().toString());
//                bcDTO.setVerificationCode(client.getVerificationCode());
                dtoList.add(bcDTO);
            }
            lbcDTO.setData(dtoList);
            return Optional.ofNullable(lbcDTO);
        }
        return Optional.empty();
    }

    public Optional<ClientDTO> findClientDTOByUid(String userid) {
        Optional<Client> client = clientRepo.findClientByUidAndActive(UUID.fromString(userid), true);
        if (client.isPresent()) {
            ClientDTO bcDTO = new ClientDTO();
            bcDTO.setStatus(HttpStatus.OK.value());
            bcDTO.setMessage(Constants.KEY_SUCESSE);

            bcDTO.setUserId(client.get().getUid().toString());
            bcDTO.setFirstName(client.get().getFirstName());
            bcDTO.setLastName(client.get().getLastName());
            bcDTO.setFatherName(client.get().getFatherName());
            bcDTO.setNationalId(client.get().getNationalId());
            bcDTO.setBirthDate(client.get().getBirthDate());
            bcDTO.setScore(client.get().getScore());
            bcDTO.setAvatarHashcode(client.get().getAvatarHashcode());
            bcDTO.setActive(client.get().getActive());
            bcDTO.setMobileNumber(client.get().getMobileNumber());
            bcDTO.setJobTitle(client.get().getJobTitle());
            bcDTO.setAddress(client.get().getAddress());
            bcDTO.setPostalCode(client.get().getPostalCode());
            bcDTO.setFieldOfStudy(client.get().getFieldOfStudy());
            bcDTO.setTel(client.get().getTel());
            bcDTO.setWalletId(client.get().getWallet().getUid().toString());

//            bcDTO.setVerificationCode(client.get().getVerificationCode());
            return Optional.ofNullable(bcDTO);
        }
        return Optional.empty();
    }


    public void deleteClient(String mobilenumber) {
        Optional<Client> client = clientRepo.findByMobileNumber(mobilenumber);
        if (client.isPresent())
            clientRepo.delete(client.get());
    }
}
