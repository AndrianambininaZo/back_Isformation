package com.fichier.fichier.service;

import com.fichier.fichier.dtos.ModuleRequest;
import com.fichier.fichier.entity.ModuleFormation;
import com.fichier.fichier.entity.Validation;

import java.util.List;

public interface ModuleService {
    ModuleFormation saveModule(ModuleRequest request);
    void addModuleToUser(Integer code, String idUser);

    List<ModuleFormation> listModule();
    Long findMaxByCode();

    List<Validation> listModuleFindByClient(String idClient);
}
