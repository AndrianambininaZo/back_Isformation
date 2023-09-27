package com.fichier.fichier.repository;

import com.fichier.fichier.entity.ModuleFormation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ModuleRepository extends JpaRepository<ModuleFormation,Long> {
    ModuleFormation findByCode(Integer code);

    @Query("SELECT MAX(m.code) FROM ModuleFormation m " )
    Long findByMaxCode();
}
