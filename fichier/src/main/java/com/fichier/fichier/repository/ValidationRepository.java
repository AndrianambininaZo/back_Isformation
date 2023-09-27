package com.fichier.fichier.repository;

import com.fichier.fichier.entity.User;
import com.fichier.fichier.entity.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValidationRepository extends JpaRepository<Validation,Long> {
    List<Validation> findByUser(User user);
}
