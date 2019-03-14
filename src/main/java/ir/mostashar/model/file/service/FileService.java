package ir.mostashar.model.file.service;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.dto.FileForm;
import ir.mostashar.model.client.dto.FileUpdateForm;
import ir.mostashar.model.client.repository.ClientRepo;
import ir.mostashar.model.file.File;
import ir.mostashar.model.file.dto.FileDTO;
import ir.mostashar.model.file.dto.ListFileDTO;
import ir.mostashar.model.file.repository.FileRepo;
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
public class FileService {


    @Autowired
    ClientRepo clientRepo;

    @Autowired
    FileRepo fileRepo;

    @Value("${mostashar.app.fileNumber}")
    private String fileNumber;

    /**
     * create file from client
     *
     * @param fileForm
     * @return
     */
    public UUID createFile(FileForm fileForm) {
        Optional<Client> client = clientRepo.findClientByUidAndActive(UUID.fromString(fileForm.getUserId()),true);
        UUID uuid;
        if (client.isPresent()) {
            uuid = UUID.randomUUID();
            File file = new File();
            file.setUid(uuid);
            Long maxFileNumber = fileRepo.findMaxFileNumber();
            if (maxFileNumber!= null){
                file.setFileNumber(String.valueOf(maxFileNumber));
            }else{
                file.setFileNumber(fileNumber);
            }
            file.setTitle(fileForm.getTitle());
            file.setDescription(fileForm.getDescription());
            file.setCreationDate(System.currentTimeMillis());
            file.setClient(client.get());
            fileRepo.save(file);
            return uuid;
        }
        return null;
    }

    /**
     * check file title exist
     *
     * @param title
     * @return
     */
    public boolean existTitleFile(String title, Client client,boolean isDeleted) {
        Optional<Boolean> aBoolean = fileRepo.existsByClientAndTitleAndDeleted(client, title,isDeleted);
        if (aBoolean.isPresent())
            return aBoolean.get();
        else
            return false;
    }

    public boolean deleteFileByUid(File file) {
//        Optional<File> file = fileRepository.findByUidAndDeleted(UUID.fromString(fileId),false);
        if (file != null ) {
            file.setDeleted(true);
            fileRepo.save(file);
            return true;
        }
        return false;
    }

    public boolean updateFile(FileUpdateForm fileUpdateForm) {
        Optional<File> file = fileRepo.findByUidAndDeleted(UUID.fromString(fileUpdateForm.getFileId()), false);
        if (file.isPresent()) {
            file.get().setTitle(fileUpdateForm.getTitle());
            file.get().setDescription(fileUpdateForm.getDescription());
            file.get().setModificationDate(System.currentTimeMillis());
            fileRepo.save(file.get());
            return true;
        }
        return false;
    }

    public Optional<FileDTO> findFileDTOByUid(String fileId) {
        Optional<File> file = fileRepo.findByUidAndDeleted(UUID.fromString(fileId),false);
        if (file.isPresent()) {
            FileDTO fileDTO = new FileDTO();
            fileDTO.setStatus(HttpStatus.OK.value());
            fileDTO.setMessage(Constants.KEY_SUCESSE);
            fileDTO.setFileId(file.get().getUid().toString());
            fileDTO.setFileNumber(file.get().getFileNumber());
            fileDTO.setTitle(file.get().getTitle());
            fileDTO.setDescription(file.get().getDescription());
            fileDTO.setCreationDate(file.get().getCreationDate());
            fileDTO.setModificationDate(file.get().getModificationDate());
            if (file.get().getClient() != null)
                fileDTO.setClientId(file.get().getClient().getUid().toString());

            return Optional.ofNullable(fileDTO);
        }
        return Optional.empty();
    }

    public Optional<ListFileDTO> findAllFileByClientUid(String clientUid) {
        Optional<List<File>> fileList = fileRepo.findAllByClientUidAndDeleted(UUID.fromString(clientUid),false);
        if (fileList.isPresent()) {

            ListFileDTO listFileDTO = new ListFileDTO();
            listFileDTO.setStatus(HttpStatus.OK.value());
            listFileDTO.setMessage(Constants.KEY_SUCESSE);

            List<FileDTO> dtoList = new ArrayList<>();
            for (File file : fileList.get()) {
                FileDTO fileDTO = new FileDTO();
                fileDTO.setFileId(file.getUid().toString());
                fileDTO.setTitle(file.getTitle());
                fileDTO.setFileNumber(file.getFileNumber());
                fileDTO.setDescription(file.getDescription());
                fileDTO.setCreationDate(file.getCreationDate());
                fileDTO.setModificationDate(file.getModificationDate());
                if (file.getClient() != null)
                    fileDTO.setClientId(file.getClient().getUid().toString());

                dtoList.add(fileDTO);
            }
            listFileDTO.setFiles(dtoList);
            return Optional.of(listFileDTO);
        }
        return Optional.empty();
    }

    public Optional<ListFileDTO> findAllCleintFileByRequestId(String requestId,String clientUid) {
        Optional<List<File>> fileList = fileRepo.findAllByRequestId(UUID.fromString(clientUid),requestId,false);
        if (fileList.isPresent()) {

            ListFileDTO listFileDTO = new ListFileDTO();
            listFileDTO.setStatus(HttpStatus.OK.value());
            listFileDTO.setMessage(Constants.KEY_SUCESSE);

            List<FileDTO> dtoList = new ArrayList<>();
            for (File file : fileList.get()) {
                FileDTO fileDTO = new FileDTO();
                fileDTO.setFileId(file.getUid().toString());
                fileDTO.setTitle(file.getTitle());
                fileDTO.setFileNumber(file.getFileNumber());
                fileDTO.setDescription(file.getDescription());
                fileDTO.setCreationDate(file.getCreationDate());
                fileDTO.setModificationDate(file.getModificationDate());
                if (file.getClient() != null)
                    fileDTO.setClientId(file.getClient().getUid().toString());

                dtoList.add(fileDTO);
            }
            listFileDTO.setFiles(dtoList);
            return Optional.of(listFileDTO);
        }
        return Optional.empty();
    }
    public Optional<ListFileDTO> findAllFileByRequestId(String requestId,String clientUid) {
        Optional<List<File>> fileList = fileRepo.findAllByRequestId(UUID.fromString(clientUid),requestId,false);
        if (fileList.isPresent()) {

            ListFileDTO listFileDTO = new ListFileDTO();
            listFileDTO.setStatus(HttpStatus.OK.value());
            listFileDTO.setMessage(Constants.KEY_SUCESSE);

            List<FileDTO> dtoList = new ArrayList<>();
            for (File file : fileList.get()) {
                FileDTO fileDTO = new FileDTO();
                fileDTO.setFileId(file.getUid().toString());
                fileDTO.setTitle(file.getTitle());
                fileDTO.setFileNumber(file.getFileNumber());
                fileDTO.setDescription(file.getDescription());
                fileDTO.setCreationDate(file.getCreationDate());
                fileDTO.setModificationDate(file.getModificationDate());
                if (file.getClient() != null)
                    fileDTO.setClientId(file.getClient().getUid().toString());

                dtoList.add(fileDTO);
            }
            listFileDTO.setFiles(dtoList);
            return Optional.of(listFileDTO);
        }
        return Optional.empty();
    }

    public Optional<File> findFileByUid(String fileId) {
        Optional<File> file = fileRepo.findByUidAndDeleted(UUID.fromString(fileId),false);
        if (file.isPresent())
            return Optional.ofNullable(file.get());
        else
            return Optional.empty();
    }

}
