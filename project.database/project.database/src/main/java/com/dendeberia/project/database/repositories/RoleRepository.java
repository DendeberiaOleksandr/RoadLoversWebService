package com.dendeberia.project.database.repositories;

import com.dendeberia.project.database.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
