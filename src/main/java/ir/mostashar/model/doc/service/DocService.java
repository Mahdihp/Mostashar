package ir.mostashar.model.doc.service;

import ir.mostashar.model.client.repository.ClientRepo;
import ir.mostashar.model.doc.DocType;
import ir.mostashar.model.user.service.UserServiceImpl;
import ir.mostashar.model.doc.Doc;
import ir.mostashar.model.doc.MimeType;
import ir.mostashar.model.doc.dto.DocDTO;
import ir.mostashar.model.doc.dto.ListDocDTO;
import ir.mostashar.model.doc.repository.DocRepo;
import ir.mostashar.model.file.File;
import ir.mostashar.model.file.repository.FileRepo;
import ir.mostashar.model.user.User;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DocService {


    @Autowired
    DocRepo docRepo;

    @Autowired
    FileRepo fileRepo;

    @Autowired
    ClientRepo clientRepo;

    @Autowired
    UserServiceImpl userService;

    public UUID createDoc(File file, int docType, MultipartFile multipartFile) {
        Doc doc = new Doc();
        UUID uuid;

        byte[] arrayData = new byte[(int) multipartFile.getSize()];
        try {
//            doc.setHashCode(String.valueOf(multipartFile.hashCode()));
//            multipartFile.getInputStream().read(arrayData);
            doc.setData(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        uuid = UUID.randomUUID();
        doc.setUid(uuid);
//            doc.setChecksum(docForm.getChecksum());
        switch (docType) {
            case 0:
                doc.setMimeType(MimeType.Audio);
                break;
            case 1:
                doc.setMimeType(MimeType.Video);
                break;
            case 2:
                doc.setMimeType(MimeType.PDF);
                break;
            case 3:
                doc.setMimeType(MimeType.Picture);
                break;
            case 4:
                doc.setMimeType(MimeType.Text);
                break;
            case 5:
                doc.setMimeType(MimeType.ZipFile);
                break;
            case 6:
                doc.setMimeType(MimeType.RARFile);
                break;
        }
        doc.setFile(file);
        doc.setCreationDate(System.currentTimeMillis());
        Doc save = docRepo.save(doc);
        if (save != null)
            return uuid;
        else
            return null;
    }

    public Optional<ListDocDTO> findAllByWithoutDataUid(String userId, String fileId) {
        Optional<File> file = fileRepo.findByUidAndClientUidAndDeleted(UUID.fromString(fileId), UUID.fromString(userId), false);
        if (file.isPresent()) {
            Optional<List<Doc>> docs = docRepo.findAllByFileUidAndDeleted(file.get().getUid(), false);
            if (docs.isPresent()) {
                List<DocDTO> dtoList = new ArrayList<>();
                ListDocDTO listDocDTO = new ListDocDTO();
                for (Doc doc : docs.get()) {
                    DocDTO docDTO = new DocDTO();
                    docDTO.setDocId(doc.getUid().toString());
                    docDTO.setChecksum(doc.getChecksum());
                    docDTO.setHashCode(doc.getHashCode());
                    docDTO.setMimeType(doc.getMimeType().type + "");
                    docDTO.setCreationDate(doc.getCreationDate());
                    docDTO.setFileId(doc.getFile().getUid().toString());
                    dtoList.add(docDTO);
                }
                listDocDTO.setStatus(HttpStatus.OK.value());
                listDocDTO.setMessage(Constants.KEY_SUCESSE);
                listDocDTO.setDocs(dtoList);
                return Optional.ofNullable(listDocDTO);
            }
        }
        return Optional.empty();
    }

    public Optional<ListDocDTO> findAllresumeBylawyerid(String lawyerid) {

            Optional<List<Doc>> docs = docRepo.findAllDocsBylawyerid(lawyerid,DocType.Resume);
            if (docs.isPresent()) {
                List<DocDTO> dtoList = new ArrayList<>();
                ListDocDTO listDocDTO = new ListDocDTO();
                for (Doc doc : docs.get()) {
                    DocDTO docDTO = new DocDTO();
                    docDTO.setDocId(doc.getUid().toString());
                    docDTO.setChecksum(doc.getChecksum());
                    docDTO.setHashCode(doc.getHashCode());
                    docDTO.setMimeType(doc.getMimeType().type + "");
                    docDTO.setCreationDate(doc.getCreationDate());
                    docDTO.setFileId(doc.getFile().getUid().toString());
                    dtoList.add(docDTO);
                }
                listDocDTO.setStatus(HttpStatus.OK.value());
                listDocDTO.setMessage(Constants.KEY_SUCESSE);
                listDocDTO.setDocs(dtoList);
                return Optional.ofNullable(listDocDTO);
            }
        return Optional.empty();
    }

    public Optional<DocDTO> findByWithoutDataUid(String docId, String userid, String fileId) {
        Optional<User> user = userService.findUserByUid(userid);
        if (!user.isPresent())
            return Optional.empty();

        Optional<Doc> doc = docRepo.findByUidAndFileUidAndDeleted(UUID.fromString(docId),UUID.fromString(fileId), false);
        if (doc.isPresent()) {
            if (doc.get().getFile().getUid().toString().equals(fileId)) {
                DocDTO docDTO = new DocDTO();
                docDTO.setDocId(doc.get().getUid().toString());
                docDTO.setStatus(HttpStatus.OK.value());
                docDTO.setMessage(Constants.KEY_SUCESSE);
                docDTO.setCreationDate(doc.get().getCreationDate());
                docDTO.setMimeType(doc.get().getMimeType().type + "");
                docDTO.setFileId(doc.get().getFile().getUid().toString());
                return Optional.ofNullable(docDTO);
            }
        }
        return Optional.empty();
    }

    public byte[] findDocDataByUid(Optional<Doc> doc) {
        if (doc.isPresent()) {
            Doc docData = doc.get();
            return docData.getData();
        }
        return null;
    }

    public Optional<Doc> findByUid(String docId) {
        Optional<Doc> doc = docRepo.findByUid(UUID.fromString(docId));
        if (doc.isPresent())
            return doc;
        else
            return Optional.empty();

    }

    public Optional<Doc> findBylawyerid(String lawyerId) {
        Optional<Doc> doc = docRepo.findByUid(UUID.fromString(docId));
        if (doc.isPresent())
            return doc;
        else
            return Optional.empty();

    }

    public Optional<Doc> findByUid(String docId, String userid, String fileId) {
        Optional<User> user = userService.findUserByUid(userid);
        if (!user.isPresent())
            return Optional.empty();

        Optional<Doc> doc = docRepo.findByUidAndFileUidAndDeleted(UUID.fromString(docId),UUID.fromString(fileId), false);
        if (doc.isPresent())
            return doc;
        else
            return Optional.empty();

    }

    public boolean deleteDoc(String docId, String userid, String fileId) {
        Optional<User> user = userService.findUserByUid(userid);
        if (!user.isPresent())
            return false;

        Optional<Doc> doc = docRepo.findByUidAndFileUidAndDeleted(UUID.fromString(docId),UUID.fromString(fileId), false);
        if (doc.isPresent()) {
            doc.get().setDeleted(true);
            docRepo.save(doc.get());
            return true;
        }
        return false;
    }
}
