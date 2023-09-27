package com.fichier.fichier.service.impl;

import com.fichier.fichier.dtos.ModuleRequest;
import com.fichier.fichier.entity.ModuleFormation;
import com.fichier.fichier.entity.Role;
import com.fichier.fichier.entity.User;
import com.fichier.fichier.entity.Validation;
import com.fichier.fichier.repository.ModuleRepository;
import com.fichier.fichier.repository.UserRepository;
import com.fichier.fichier.repository.ValidationRepository;
import com.fichier.fichier.service.ModuleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class ModuleServiceImpl implements ModuleService {
    private ModuleRepository moduleRepository;
    private UserRepository userRepository;
    private ValidationRepository validationRepository;
    @Override
    public ModuleFormation saveModule(ModuleRequest request) {
        Long code =moduleRepository.findByMaxCode();
        if (code==null){
            code = 0L;
        }
        ModuleFormation formation=new ModuleFormation();
        formation.setDate(request.getDate());
        formation.setNom(request.getNom());
        formation.setCode(Math.toIntExact(code) + 1);
        return moduleRepository.save(formation);
    }
    @Override
    public void addModuleToUser(Integer code, String idUser) {
       ModuleFormation formation=moduleRepository.findByCode(code);
       User user=userRepository.findById(idUser).get();
        Validation validation=new Validation();
        validation.setUser(user);
        validation.setFormation(formation);
        validationRepository.save(validation);
        //user.getRole().add(role);
        }

    @Override
    public List<ModuleFormation> listModule() {
        return moduleRepository.findAll();
    }

    @Override
    public Long findMaxByCode() {
        Long code=moduleRepository.findByMaxCode();
        if(code==null){
            return (long) (0 + 1);
        }else {
            return moduleRepository.findByMaxCode() +  1;
        }
    }

    @Override
    public List<Validation> listModuleFindByClient(String idClient) {
        User user=userRepository.findById(idClient).get();
        return null;
    }


}
