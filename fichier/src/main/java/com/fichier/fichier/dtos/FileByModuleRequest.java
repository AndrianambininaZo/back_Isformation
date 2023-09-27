package com.fichier.fichier.dtos;

import com.fichier.fichier.entity.ModuleFormation;
import lombok.Data;

import javax.persistence.ManyToOne;
import java.util.Date;
@Data
public class FileByModuleRequest {
    private Long id;
    private String fileName;
    private String type;
    private String extension;
    private Date date;
    private Long idModule;
}
