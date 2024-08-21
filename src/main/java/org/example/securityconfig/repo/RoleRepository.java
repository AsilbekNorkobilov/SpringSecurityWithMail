package org.example.securityconfig.repo;

import org.example.securityconfig.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}