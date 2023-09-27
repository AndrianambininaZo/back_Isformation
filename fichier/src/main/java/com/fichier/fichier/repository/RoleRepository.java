package com.fichier.fichier.repository;

import com.fichier.fichier.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByNomRole(String nomRome);
}
