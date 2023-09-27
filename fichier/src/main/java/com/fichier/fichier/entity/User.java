package com.fichier.fichier.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fichier.fichier.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class
User {
    @Id
    private String id;
    private String nom;
    private Integer status;
    private Integer etat;
    private String prenom;
    private String adresse;
    private String telephone;
    @Column(unique = true)
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> role=new ArrayList<>();
}
