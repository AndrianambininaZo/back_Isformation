package com.fichier.fichier.repository;

import com.fichier.fichier.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findByEmail(String email);
}
