package com.fichier.fichier.repository;

import com.fichier.fichier.entity.FileByModule;
import com.fichier.fichier.entity.ModuleFormation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileByModuleRepository extends JpaRepository<FileByModule,Long> {
    List<FileByModule> findByModuleAndType(ModuleFormation moduleFormation,String type);
    List<FileByModule> findByModule(ModuleFormation moduleFormation);
}
