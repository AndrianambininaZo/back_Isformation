package com.fichier.fichier.dtos;

import lombok.Data;

@Data
public class RequestPassword {
    private String id;
    private  String password;
    private String setPassword;
}
