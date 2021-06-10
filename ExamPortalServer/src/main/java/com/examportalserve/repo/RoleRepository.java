package com.examportalserve.repo;

import com.examportalserve.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long > {
}
