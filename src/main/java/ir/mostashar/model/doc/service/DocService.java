package ir.mostashar.model.doc.service;

import ir.mostashar.model.client.repository.ClientRepo;
import ir.mostashar.model.doc.Doc;
import ir.mostashar.model.doc.DocType;
import ir.mostashar.model.doc.MimeType;
import ir.mostashar.model.doc.dto.DocDTO;
import ir.mostashar.model.doc.dto.ListDocDTO;
import ir.mostashar.model.doc.repository.DocRepo;
import ir.mostashar.model.file.File;
import ir.mostashar.model.file.repository.FileRepo;
import ir.mostashar.model.user.User;
import ir.mostashar.model.user.service.UserServiceImpl;
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
    private DocRepo docRepo;

    @Autowired
    private FileRepo fileRepo;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private UserServiceImpl userService;

    public UUID create(File file, int mimeType, int docType, MultipartFile multipartFile) {
        Doc doc = new Doc();
        UUID uuid;

//        byte[] arrayData = new byte[(int) multipartFile.getSize()];
        try {
//            doc.setHashCode(String.valueOf(multipartFile.hashCode()));
//            multipartFile.getInputStream().read(arrayData);
            doc.setData(multipartFile.getBytes());

            uuid = UUID.randomUUID();
            doc.setUid(uuid);
//            doc.setChecksum(docForm.getChecksum());
            switch (mimeType) {
                case 0:
                    doc.setMimeType(MimeType.AUDIO);
                    break;
                case 1:
                    doc.setMimeType(MimeType.VIDEO);
                    break;
                case 2:
                    doc.setMimeType(MimeType.PDF);
                    break;
                case 3:
                    doc.setMimeType(MimeType.PICTURE);
                    break;
                case 4:
                    doc.setMimeType(MimeType.TEXT);
                    break;
                case 5:
                    doc.setMimeType(MimeType.ZIP_FILE);
                    break;
                case 6:
                    doc.setMimeType(MimeType.RAR_FILE);
                    break;
            }
            switch (docType) {
                case 0:
                    doc.setDocType(DocType.FILE);
                    break;
                case 1:
                    doc.setDocType(DocType.RESUME);
                    break;

            }
            doc.setFile(file);
            doc.setCreationDate(System.currentTimeMillis());
            docRepo.save(doc);
            return uuid;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Optional<ListDocDTO> findAllByIdWithoutData(String userId, String fileId) {
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

    public Optional<ListDocDTO> findAllResumeByLawyerId(String lawyerid) {

        Optional<List<Doc>> docs = docRepo.findAllByUserUidAndDocTypeAndDeleted(UUID.fromString(lawyerid), DocType.RESUME, false);
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

    public Optional<DocDTO> findByIdWithoutData(String docId, String userid, String fileId) {
        Optional<User> user = userService.findById(userid);
        if (!user.isPresent())
            return Optional.empty();

        Optional<Doc> doc = docRepo.findByUidAndFileUidAndDeleted(UUID.fromString(docId), UUID.fromString(fileId), false);
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

    public Optional<Doc> findById(String docId) {
        Optional<Doc> doc = docRepo.findByUid(UUID.fromString(docId));
        if (doc.isPresent())
            return doc;
        else
            return Optional.empty();

    }

    public Optional<Doc> findById(String docId, String userid, String fileId) {
        Optional<User> user = userService.findById(userid);
        if (!user.isPresent())
            return Optional.empty();

        Optional<Doc> doc = docRepo.findByUidAndFileUidAndDeleted(UUID.fromString(docId), UUID.fromString(fileId), false);
        if (doc.isPresent())
            return doc;
        else
            return Optional.empty();

    }

    public boolean deleteDoc(String docId, String userid, String fileId) {
        Optional<User> user = userService.findById(userid);
        if (!user.isPresent())
            return false;

        Optional<Doc> doc = docRepo.findByUidAndFileUidAndDeleted(UUID.fromString(docId), UUID.fromString(fileId), false);
        if (doc.isPresent()) {
            doc.get().setDeleted(true);
            docRepo.save(doc.get());
            return true;
        }
        return false;
    }
}
