package ir.mostashar.model.organization.repository;

import ir.mostashar.model.organization.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrganizationRepo extends JpaRepository<Organization, Long> {

    Optional<Organization> findByUid(UUID uuid);
    Optional<Organization> findByUidAndVerified(UUID uuid,boolean isVerified);
    Optional<Organization> findByUsernameAndName(String userName,String name);

    Optional<List<Organization>> findAllByVerified(boolean isVerified);

    Optional<List<Organization>> findAllByNameLikeAndVerified(String name,boolean isVerified);
    Optional<List<Organization>> findAllByNameAndVerified(String name,boolean isVerified);

    List<Organization> findAllByName(Pageable pageRequest);





}
