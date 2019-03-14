package ir.mostashar.model.rightMessage.service;

import ir.mostashar.model.rightMessage.RightMessage;
import ir.mostashar.model.rightMessage.dto.ListRightMessageDTO;
import ir.mostashar.model.rightMessage.dto.RightMessageDTO;
import ir.mostashar.model.rightMessage.dto.RightMessageForm;
import ir.mostashar.model.rightMessage.repository.RightMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ir.mostashar.utils.Constants;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RightMessageService {

    @Autowired
    RightMessageRepo rightMessageRepo;

    public boolean createRightMessage(RightMessageForm rmForm) {
        Optional<Boolean> exists = rightMessageRepo.existsByTitle(rmForm.getTitle());
        if (exists.isPresent() && !exists.get()) {
            RightMessage rightMessage = new RightMessage();
            rightMessage.setUid(UUID.randomUUID());
            rightMessage.setTitle(rmForm.getTitle());
            rightMessage.setDescription(rmForm.getDescription());
            rightMessage.setCreationDate(System.currentTimeMillis());
            rightMessage.setActive(false);
//            rightMessage.setExpiryDate(); پرسیده شود
            rightMessageRepo.save(rightMessage);
            return true;
        }
        return true;
    }

    public boolean updateRightMessage(RightMessageForm rmForm) {
        Optional<RightMessage> rightMessage = rightMessageRepo.findByUid(UUID.fromString(rmForm.getUid()));
        if (rightMessage.isPresent()) {
            rightMessage.get().setTitle(rmForm.getTitle());
            rightMessage.get().setDescription(rmForm.getDescription());
            rightMessage.get().setCreationDate(System.currentTimeMillis());
            rightMessage.get().setActive(false);
//            rightMessage.get().setExpiryDate(); پرسیده شود
            rightMessageRepo.save(rightMessage.get());
            return true;
        }
        return true;
    }

    public Optional<RightMessage> findRightMessageByUid(String uid) {
        Optional<RightMessage> rightMessage = rightMessageRepo.findByUid(UUID.fromString(uid));
        if (rightMessage.isPresent()) {
            return Optional.ofNullable(rightMessage.get());
        }
        return Optional.empty();
    }

    public Optional<ListRightMessageDTO> findRightMessageBylawyer(String lawyer) {
        Optional<List<RightMessage>> rightMessages = rightMessageRepo.findBylawyer(lawyer);
        if (rightMessages.isPresent()) {
            ListRightMessageDTO listRightMessageDTO=new ListRightMessageDTO();
            List<RightMessageDTO> dtoList = new ArrayList<>();
            for(RightMessage rm:rightMessages.get()){
                RightMessageDTO rightMessageDTO=new RightMessageDTO();
                rightMessageDTO.setUid(rm.getUid());
                rightMessageDTO.setTitle(rm.getTitle());
                rightMessageDTO.setDescription(rm.getDescription());
                rightMessageDTO.setCreationDate(rm.getCreationDate());
                rightMessageDTO.setExpiryDate(rm.getExpiryDate());
                dtoList.add(rightMessageDTO);
            }
            listRightMessageDTO.setStatus(HttpStatus.OK.value());
            listRightMessageDTO.setMessage(Constants.KEY_SUCESSE);
            listRightMessageDTO.setRightMessageDTOList(dtoList);
            return Optional.ofNullable(listRightMessageDTO);
        }
        return Optional.empty();
    }

    public Optional<ListRightMessageDTO> findRightMessageByuser(String user) {
        Optional<List<RightMessage>> rightMessages = rightMessageRepo.findByuser(user);
        if (rightMessages.isPresent()) {
            ListRightMessageDTO listRightMessageDTO=new ListRightMessageDTO();
            List<RightMessageDTO> dtoList = new ArrayList<>();
            for(RightMessage rm:rightMessages.get()){
                RightMessageDTO rightMessageDTO=new RightMessageDTO();
                rightMessageDTO.setUid(rm.getUid());
                rightMessageDTO.setTitle(rm.getTitle());
                rightMessageDTO.setDescription(rm.getDescription());
                rightMessageDTO.setCreationDate(rm.getCreationDate());
                rightMessageDTO.setExpiryDate(rm.getExpiryDate());
                dtoList.add(rightMessageDTO);
            }
            listRightMessageDTO.setStatus(HttpStatus.OK.value());
            listRightMessageDTO.setMessage(Constants.KEY_SUCESSE);
            listRightMessageDTO.setRightMessageDTOList(dtoList);
            return Optional.ofNullable(listRightMessageDTO);
        }
        return Optional.empty();
    }

    public Optional<List<RightMessage>> findAllRightMessage() {
        List<RightMessage> list = rightMessageRepo.findAll();
        if (list != null) {
            return Optional.ofNullable(list);
        }
        return Optional.empty();
    }

}
