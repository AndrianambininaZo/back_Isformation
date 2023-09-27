package com.fichier.fichier.service;

import com.fichier.fichier.dtos.FileByModuleRequest;
import com.fichier.fichier.entity.FileByModule;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileByModuleService {
    FileByModule saveFileByModule(FileByModuleRequest request);
    String saveFile(MultipartFile file, Long id) throws IOException;

    String saveFileExamen(MultipartFile file, Long id) throws IOException;

    String saveFileCorrection(MultipartFile file, Long id) throws IOException;
    List<FileByModule> listFileByModule(Long idModule);
    FileByModule fileByModule(Long idModule);
}
