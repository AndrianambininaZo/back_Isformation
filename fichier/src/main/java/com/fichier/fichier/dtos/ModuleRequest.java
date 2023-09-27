package com.fichier.fichier.dtos;

import lombok.Data;

import java.util.Date;
@Data
public class ModuleRequest {
    private Long id;
    private String nom;
    private Date date;
    private Integer code;
}
