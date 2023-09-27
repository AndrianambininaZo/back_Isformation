package com.fichier.fichier.repository;

import com.fichier.fichier.entity.Examen;
import com.fichier.fichier.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamenRepository extends JpaRepository<Examen,Long> {
    List<Examen> findByUser(User user);
}
