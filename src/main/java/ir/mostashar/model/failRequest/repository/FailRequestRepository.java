package ir.mostashar.model.failRequest.repository;

import ir.mostashar.model.failRequest.FailRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FailRequestRepository extends JpaRepository<FailRequest,Long> {


}
