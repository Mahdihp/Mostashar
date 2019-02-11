package ir.mostashar.model.file.repository;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.file.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File,Long> {

    Optional<Boolean> existsByClientAndTitle(Client client, String title);

    Optional<File> findFileByUid(String uid);

    Optional<List<File>> findAllByClientUid(String uid);

}
