package ir.mostashar.model.doc.repository;

import ir.mostashar.model.doc.Doc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocRepository extends JpaRepository<Doc,Long> {


    Optional<Doc> findByUid(UUID uuid);
}
