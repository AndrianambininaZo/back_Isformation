package com.fichier.fichier.dtos;

import lombok.Data;

@Data
public class JwtRequest {
    private String codeEntree;
    private String username;
    private String password;
}
