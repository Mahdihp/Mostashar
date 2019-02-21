package ir.mostashar.model.file.repository;

import ir.mostashar.model.client.Client;
import ir.mostashar.model.file.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileRepo extends JpaRepository<File,Long> {

    Optional<Boolean> existsByClientAndTitle(Client client, String title);

    Optional<File> findFileByUidAndDeleted(UUID uid,boolean isDeleted);

    Optional<List<File>> findAllByDeleted(boolean isDeleted);
    Optional<List<File>> findAllByClientUidAndDeleted(UUID uuid,boolean isDeleted);



}
