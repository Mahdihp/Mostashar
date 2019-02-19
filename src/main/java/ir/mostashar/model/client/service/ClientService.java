package ir.mostashar.model.client.service;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.dto.BaseClientDTO;
import ir.mostashar.model.client.dto.ListBaseClientDTO;
import ir.mostashar.model.client.repository.ClientRepository;
import ir.mostashar.model.user.User;
import ir.mostashar.model.user.repository.UserRepository;
import ir.mostashar.util.Constants;
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
    private ClientRepository clientRepository;


    public Optional<Client> findByClientId(UUID userid) {
        Optional<Client> client = clientRepository.findByUid(userid);
        if (client.isPresent())
            return Optional.ofNullable(client.get());
        else
            return Optional.empty();
    }

    public Optional<ListBaseClientDTO> findAll() {
        List<Client> clients = clientRepository.findAll();
        if (clients != null) {
            ListBaseClientDTO lbcDTO = new ListBaseClientDTO();
            lbcDTO.setStatus(HttpStatus.OK.value());
            lbcDTO.setMessage(Constants.KEY_SUCESSE);
            List<BaseClientDTO> dtoList = new ArrayList<>();
            for (Client client : clients) {
                BaseClientDTO bcDTO = new BaseClientDTO();
                bcDTO.setUid(client.getUid().toString());
                bcDTO.setFirstName(client.getFirstName());
                bcDTO.setLastName(client.getLastName());
                bcDTO.setFatherName(client.getFatherName());
                bcDTO.setUsername(client.getUsername());
                bcDTO.setPassword(client.getPassword());
                bcDTO.setNationalId(client.getNationalId());
                bcDTO.setBirthDate(client.getBirthDate());
                bcDTO.setIsOnline(client.isOnline());
                bcDTO.setScore(client.getScore());
                bcDTO.setAvatarHashcode(client.getAvatarHashcode());
                bcDTO.setIsActive(client.isActive());
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

    public Optional<BaseClientDTO> findByUid(String userid) {
        Optional<Client> client = clientRepository.findByUid(UUID.fromString(userid));
        if (client.isPresent()) {
            BaseClientDTO bcDTO = new BaseClientDTO();
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
            bcDTO.setIsOnline(client.get().isOnline());
            bcDTO.setScore(client.get().getScore());
            bcDTO.setAvatarHashcode(client.get().getAvatarHashcode());
            bcDTO.setIsActive(client.get().isActive());
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
