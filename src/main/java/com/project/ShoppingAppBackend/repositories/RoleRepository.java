package com.project.ShoppingAppBackend.repositories;

import com.project.ShoppingAppBackend.models.Role;
import com.project.ShoppingAppBackend.models.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(RoleName roleName);
}
