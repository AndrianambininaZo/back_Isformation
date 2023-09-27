package com.fichier.fichier.service.impl;

import com.fichier.fichier.dtos.ExamenRequest;
import com.fichier.fichier.entity.Examen;
import com.fichier.fichier.entity.FileByModule;
import com.fichier.fichier.entity.ModuleFormation;
import com.fichier.fichier.entity.User;
import com.fichier.fichier.repository.ExamenRepository;
import com.fichier.fichier.repository.FileByModuleRepository;
import com.fichier.fichier.repository.ModuleRepository;
import com.fichier.fichier.repository.UserRepository;
import com.fichier.fichier.service.ExamenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ExamenServiceImpl implements ExamenService {
    private ExamenRepository  examenRepository;
    private ModuleRepository repository;
    private UserRepository userRepository;
    private FileByModuleRepository moduleRepository;
    @Override
    public Examen saveExamen(ExamenRequest request) {
        /*
        Examen examen=new Examen();
        examen.setDate(request.getDate());
        examen.setStatus(request.getStatus());
        System.out.println(request);
        User user=userRepository.findById(request.getIdUser()).get();
        FileByModule fileByModule=moduleRepository.findById(request.getIdFile()).get();
        examen.setUser(user);
        examen.setFileByModule(fileByModule);*/
        Examen examen=examenRepository.findById(request.getId()).get();
        examen.setStatus(1);
        return examenRepository.save(examen);
    }

    @Override
    public void addUserByExamen(User user, Long idModule) {
        ModuleFormation module=repository.findById(idModule).get();
        List<FileByModule> list=moduleRepository.findByModuleAndType(module,"document");
        Examen examen=new Examen();
        for (FileByModule fileByModule:list){
            examen.setUser(user);
            examen.setStatus(0);
            examen.setFileByModule(fileByModule);
            examen.setDate(new Date());
            examenRepository.save(examen);
        }

    }

    @Override
    public List<Examen> listExamen() {
        return examenRepository.findAll();
    }

    @Override
    public List<Examen> listExamenFindBy(String idClient) {
        User user=userRepository.findById(idClient).get();
        return examenRepository.findByUser(user);
    }
}
