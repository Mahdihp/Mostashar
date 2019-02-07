package ir.mostashar.model.file.repository;

import ir.mostashar.model.file.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File,Long> {

    Optional<Boolean> existsByTitle(String title);

}
