package ir.mostashar.model.doc.repository;

import ir.mostashar.model.doc.Doc;
import ir.mostashar.model.doc.DocType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocRepo extends JpaRepository<Doc, Long> {


    Optional<Doc> findByUidAndLawyerUidAndDeleted(UUID uid, UUID lawyerUid, boolean isDelete);
    Optional<Doc> findByUid(UUID uuid);

    Optional<Doc> findByUidAndFileUidAndDeleted(UUID uid, UUID fileUid, boolean isDelete);

    Optional<List<Doc>> findAllByFileUidAndDeleted(UUID file,boolean isDelete);

    Optional<List<Doc>> findAllByLawyerUidAndDocTypeAndDeleted(UUID lawyerUid, DocType docType, boolean isDelete);
}
