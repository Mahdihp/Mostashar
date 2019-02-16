package ir.mostashar.model.doc.service;

import ir.mostashar.model.doc.Doc;
import ir.mostashar.model.doc.DocType;
import ir.mostashar.model.doc.dto.DocDTO;
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
import java.util.Optional;
import java.util.UUID;

@Service
public class DocService {


    @Autowired
    DocRepository docRepository;

    @Autowired
    FileRepository fileRepository;

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

    public Optional<DocDTO> findByWithoutDataUid(String docId) {
        Optional<Doc> doc = docRepository.findByUid(UUID.fromString(docId));
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
        Optional<Doc> doc = docRepository.findByUid(UUID.fromString(docid));
        if (doc.isPresent())
            return doc;
        else
            return Optional.empty();

    }
}
