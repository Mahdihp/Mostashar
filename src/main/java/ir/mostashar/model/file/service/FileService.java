package ir.mostashar.model.file.service;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.dto.FileForm;
import ir.mostashar.model.client.dto.FileUpdateForm;
import ir.mostashar.model.client.repository.ClientRepository;
import ir.mostashar.model.file.File;
import ir.mostashar.model.file.dto.BaseFileDTO;
import ir.mostashar.model.file.dto.FileDTO;
import ir.mostashar.model.file.repository.FileRepository;
import ir.mostashar.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileService {


    @Autowired
    ClientRepository clientRepository;

    @Autowired
    FileRepository fileRepository;

    /**
     * create file from client
     *
     * @param fileForm
     * @return
     */
    public UUID createFile(FileForm fileForm) {
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

    /**
     * check file title exist
     * @param title
     * @return
     */
    public boolean existTitleFile(String title, Client client) {
        Optional<Boolean> aBoolean = fileRepository.existsByClientAndTitle(client, title);
        if (aBoolean.isPresent())
            return aBoolean.get();
        else
            return false;
    }

    public boolean deleteFileByUid(String fileId) {
        Optional<File> file = fileRepository.findFileByUid(UUID.fromString(fileId));
        if (file.isPresent()) {
            fileRepository.delete(file.get());
            return true;
        }
        return false;
    }

    public boolean updateFile(FileUpdateForm fileUpdateForm) {
        Optional<File> file = fileRepository.findFileByUid(UUID.fromString(fileUpdateForm.getUid()));
        if (file.isPresent()) {
            file.get().setTitle(fileUpdateForm.getTitle());
            file.get().setDescription(fileUpdateForm.getDescription());
            file.get().setModificationDate(System.currentTimeMillis());
            fileRepository.save(file.get());
            return true;
        }
        return false;
    }

    public Optional<FileDTO> findFileByUid(String fileId) {
        Optional<File> file = fileRepository.findFileByUid(UUID.fromString(fileId));
        if (file.isPresent()) {
            FileDTO fileDTO = new FileDTO();
            BaseFileDTO baseFileDTO = new BaseFileDTO();
            fileDTO.setStatus("200");
            fileDTO.setMessage(Constants.KEY_SUCESSE);
            baseFileDTO.setFileId(file.get().getUid().toString());
            baseFileDTO.setTitle(file.get().getTitle());
            baseFileDTO.setDescription(file.get().getDescription());
            baseFileDTO.setCreationDate(file.get().getCreationDate());
            baseFileDTO.setModificationDate(file.get().getModificationDate());
            if (file.get().getClient() != null)
                baseFileDTO.setClientid(file.get().getClient().getUid().toString());
            fileDTO.setFile(baseFileDTO);
            return Optional.ofNullable(fileDTO);
        }
        return Optional.empty();
    }

    public Optional<FileDTO> findAllFileByUserId(String userid) {
        Optional<List<File>> fileList = fileRepository.findAllByClientUid(UUID.fromString(userid));
        if (fileList.isPresent()) {
            FileDTO fileDTO = new FileDTO();
            BaseFileDTO baseFileDTO = new BaseFileDTO();
            fileDTO.setStatus("200");
            fileDTO.setMessage(Constants.KEY_SUCESSE);
            fileDTO.setFiles(FileDTO.convertListFileToListFileDTO(fileList.get()));
            return Optional.ofNullable(fileDTO);
        }
        return Optional.empty();
    }

}
