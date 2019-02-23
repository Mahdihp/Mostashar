package ir.mostashar.model.calls.repository;

import ir.mostashar.model.calls.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallRepo extends JpaRepository<Call,Long> {


}
