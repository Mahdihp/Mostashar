package ir.mostashar.model.doc.service;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.client.repository.ClientRepository;
import ir.mostashar.model.doc.Doc;
import ir.mostashar.model.doc.DocType;
import ir.mostashar.model.doc.dto.DocDTO;
import ir.mostashar.model.doc.dto.ListDocDTO;
import ir.mostashar.model.doc.repository.DocRepository;
import ir.mostashar.model.file.File;
import ir.mostashar.model.file.repository.FileRepository;
import ir.mostashar.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DocService {


    @Autowired
    DocRepository docRepository;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    ClientRepository clientRepository;

    public boolean createDoc(File fileId, int docType, MultipartFile multipartFile) {
        Doc doc = new Doc();
        byte[] arrayData = new byte[(int) multipartFile.getSize()];
        try {
//            doc.setHashCode(String.valueOf(multipartFile.hashCode()));
//            multipartFile.getInputStream().read(arrayData);
            doc.setData(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        doc.setUid(UUID.randomUUID());
//            doc.setChecksum(docForm.getChecksum());
        switch (docType) {
            case 0:
                doc.setDocType(DocType.Audio);
                break;
            case 1:
                doc.setDocType(DocType.Video);
                break;
            case 2:
                doc.setDocType(DocType.PDF);
                break;
            case 3:
                doc.setDocType(DocType.Picture);
                break;
            case 4:
                doc.setDocType(DocType.Text);
                break;
            case 5:
                doc.setDocType(DocType.ZipFile);
                break;
            case 6:
                doc.setDocType(DocType.RARFile);
                break;
        }
        doc.setFile(fileId);

        doc.setCreationDate(System.currentTimeMillis());
        Doc save = docRepository.save(doc);
        if (save != null)
            return true;
        else
            return false;
    }

    public Optional<ListDocDTO> findAllByWithoutDataUid(String userId, String fileId) {
        Optional<Client> client = clientRepository.findByUid(UUID.fromString(userId));
        Optional<File> file = fileRepository.findFileByUidAndDeleted(UUID.fromString(fileId), false);
        if (client.isPresent() && file.isPresent()) {
            Optional<List<Doc>> docs = docRepository.findAllByFileUidAndDeleted(file.get().getUid() ,false);
            if (docs.isPresent()) {
                List<DocDTO> dtoList = new ArrayList<>();
                ListDocDTO listDocDTO = new ListDocDTO();
                for (Doc doc : docs.get()) {
                    DocDTO docDTO = new DocDTO();
                    docDTO.setDocId(doc.getUid().toString());
                    docDTO.setChecksum(doc.getChecksum());
                    docDTO.setHashCode(doc.getHashCode());
                    docDTO.setDocType(doc.getDocType().type + "");
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

    public Optional<DocDTO> findByWithoutDataUid(String docId) {
        Optional<Doc> doc = docRepository.findByUidAndDeleted(UUID.fromString(docId), false);
        if (doc.isPresent()) {
            DocDTO docDTO = new DocDTO();
            docDTO.setDocId(doc.get().getUid().toString());
            docDTO.setStatus(HttpStatus.OK.value());
            docDTO.setMessage(Constants.KEY_SUCESSE);
            docDTO.setCreationDate(doc.get().getCreationDate());
            docDTO.setDocType(doc.get().getDocType().type + "");
            docDTO.setFileId(doc.get().getFile().getUid().toString());
            return Optional.ofNullable(docDTO);
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


    public Optional<Doc> findByUid(String docid) {
        Optional<Doc> doc = docRepository.findByUidAndDeleted(UUID.fromString(docid), false);
        if (doc.isPresent())
            return doc;
        else
            return Optional.empty();

    }

    public boolean deleteDoc(String docUid) {
        Optional<Doc> doc = docRepository.findByUidAndDeleted(UUID.fromString(docUid), false);
        if (doc.isPresent()) {
            doc.get().setDeleted(true);
            docRepository.save(doc.get());
            return true;
        }
        return false;
    }
}
