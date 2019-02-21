package ir.mostashar.model.role.repository;

import ir.mostashar.model.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import ir.mostashar.model.role.Role;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
	Optional<Role> findByName(RoleName roleName);
}
