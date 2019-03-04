package ir.mostashar.model.doc.repository;

import ir.mostashar.model.doc.Doc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocRepo extends JpaRepository<Doc, Long> {


//    Optional<Doc> findByUidAndDeleted(UUID uuid, boolean isDelete);
    Optional<Doc> findByUidAndFileUidAndDeleted(UUID uuid,UUID fileUid, boolean isDelete);

    Optional<List<Doc>> findAllByFileUidAndDeleted(UUID file,boolean isDelete);
}
