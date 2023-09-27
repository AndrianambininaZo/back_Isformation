package com.fichier.fichier.service;

import com.fichier.fichier.dtos.RequestPassword;
import com.fichier.fichier.dtos.UserRequest;
import com.fichier.fichier.entity.Role;
import com.fichier.fichier.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(UserRequest user);
    User saveUserAdmin(UserRequest request);
    List<User> listUser();
    User user(String id);
    void addRoleToUser (String email,String nomRole);
    Role saveRol(Role role);

    User modifierStatus(String id);
    User userByEmail(String email);
    User modifierMotDePasse(RequestPassword requestPassword);
    void initialisationSuperAdmin();
}
