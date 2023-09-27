package com.fichier.fichier.service;

import com.fichier.fichier.dtos.ExamenRequest;
import com.fichier.fichier.entity.Examen;
import com.fichier.fichier.entity.User;

import java.util.List;

public interface ExamenService {
    Examen saveExamen(ExamenRequest  request);

    void addUserByExamen(User user,Long idModule);

    List<Examen>listExamen();

    List<Examen>listExamenFindBy(String idClient);
}
