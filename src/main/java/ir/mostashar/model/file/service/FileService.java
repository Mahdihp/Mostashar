package ir.mostashar.model.file.service;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.dto.FileForm;
import ir.mostashar.model.client.repository.ClientRepository;
import ir.mostashar.model.client.service.UserServiceImpl;
import ir.mostashar.model.file.File;
import ir.mostashar.model.file.repository.FileRepository;
import ir.mostashar.model.user.User;
import ir.mostashar.model.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FileService {


    @Autowired
    ClientRepository clientRepository;

    @Autowired
    FileRepository fileRepository;

    public UUID createFile(FileForm fileForm){
        Optional<Client> client = clientRepository.findByUid(UUID.fromString(fileForm.getUserId()));
        UUID uuid;
        if (client.isPresent()) {
            uuid = UUID.randomUUID();
            File file = new File();
            file.setUid(uuid);
            file.setTitle(fileForm.getTitle());
            file.setDescription(fileForm.getDescription());
            file.setCreationDate(System.currentTimeMillis());
            file.setClient(client.get());
            fileRepository.save(file);
            return uuid;
        }
        return null;
    }

    public boolean checkExistTitleFile(String title){
        Optional<Boolean> aBoolean = fileRepository.existsByTitle(title);
        if (aBoolean.isPresent())
            return true;
        else
            return false;
    }

}
