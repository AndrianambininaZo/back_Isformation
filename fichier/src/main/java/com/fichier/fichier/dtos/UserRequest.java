package com.fichier.fichier.dtos;

import com.fichier.fichier.entity.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserRequest {

        private Long id;
        private String nom;
        private Integer status;
        private String email;
        private String password;
        private String telephone;
        private String prenom;
        private String adresse;

        private List<Role> role=new ArrayList<>();
}
