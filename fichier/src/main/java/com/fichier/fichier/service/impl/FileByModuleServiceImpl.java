package com.fichier.fichier.service.impl;

import com.fichier.fichier.dtos.FileByModuleRequest;
import com.fichier.fichier.entity.FileByModule;
import com.fichier.fichier.entity.ModuleFormation;
import com.fichier.fichier.repository.FileByModuleRepository;
import com.fichier.fichier.repository.ModuleRepository;
import com.fichier.fichier.service.FileByModuleService;
import lombok.AllArgsConstructor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@AllArgsConstructor
@Transactional
public class FileByModuleServiceImpl implements FileByModuleService {
    public static final String DIRECTORY_FILE_COR=System.getProperty("user.home") + "/Download/uploads/formation/correction";
    public static final String DIRECTORY_FILE_EXM=System.getProperty("user.home") + "/Download/uploads/formation/examen";
    public static final String DIRECTORY_FILE=System.getProperty("user.home") + "/Download/uploads/formation";


    private ModuleRepository moduleRepository;
    private FileByModuleRepository fileByModuleRepository;

    @Override
    public FileByModule saveFileByModule(FileByModuleRequest request) {
        FileByModule byModule=new FileByModule();
        byModule.setFileName(request.getFileName());
        byModule.setDate(request.getDate());
        byModule.setExtension(request.getExtension());
        byModule.setType(request.getType());
        ModuleFormation moduleFormation = moduleRepository.findById(request.getIdModule()).get();
        byModule.setModule(moduleFormation);
        return fileByModuleRepository.save(byModule);
    }
    @Override
    public String saveFile(MultipartFile file, Long id) throws IOException {
        FileByModule fileByModule=fileByModuleRepository.findById(id).get();
        String fileName= StringUtils.cleanPath(file.getOriginalFilename());
        String extension="";
        if(fileName.contains(".")){
            int i=fileName.lastIndexOf('.');
            extension=i>0 ? fileName.substring(i+1) :"";
            if (extension!=""){
                Path fileStorage= get(DIRECTORY_FILE,id+"."+extension).toAbsolutePath().normalize();
                Files.copy(file.getInputStream(),fileStorage,REPLACE_EXISTING);
                fileByModule.setExtension("."+extension);
                fileByModule.setFileName(fileName);
                fileByModuleRepository.save(fileByModule);
                return "felicitation";
            }else {
                return "il y une petite erreur";
            }
        }
        return fileName;
    }
    @Override
    public String saveFileExamen(MultipartFile file, Long id) throws IOException {
        String fileName= StringUtils.cleanPath(file.getOriginalFilename());
        String extension="";
        if(fileName.contains(".")){
            int i=fileName.lastIndexOf('.');
            extension=i>0 ? fileName.substring(i+1) :"";
            if (extension!=""){
                Path fileStorage= get(DIRECTORY_FILE_EXM,id+"."+extension).toAbsolutePath().normalize();
                Files.copy(file.getInputStream(),fileStorage,REPLACE_EXISTING);
                return "felicitation";
            }else {
                return "il y une petite erreur";
            }
        }
        return fileName;
    }

    @Override
    public String saveFileCorrection(MultipartFile file, Long id) throws IOException {
        String fileName= StringUtils.cleanPath(file.getOriginalFilename());
        String extension="";
        if(fileName.contains(".")){
            int i=fileName.lastIndexOf('.');
            extension=i>0 ? fileName.substring(i+1) :"";
            if (extension!=""){
                Path fileStorage= get(DIRECTORY_FILE_COR,id+"."+extension).toAbsolutePath().normalize();
                Files.copy(file.getInputStream(),fileStorage,REPLACE_EXISTING);
                return "felicitation";
            }else {
                return "il y une petite erreur";
            }
        }
        return fileName;
    }




    @Override
    public List<FileByModule> listFileByModule(Long idModule) {
        ModuleFormation formation=moduleRepository.findById(idModule).get();
        return fileByModuleRepository.findByModule(formation);
    }

    @Override
    public FileByModule fileByModule(Long idModule) {
        return fileByModuleRepository.findById(idModule).get();
    }
}
