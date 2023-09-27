package com.fichier.fichier.web;

import com.fichier.fichier.dtos.ModuleRequest;
import com.fichier.fichier.dtos.ValidationRequest;
import com.fichier.fichier.entity.ModuleFormation;
import com.fichier.fichier.service.ModuleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class ModuleController {
    private ModuleService moduleService;
    @PostMapping("/create_module")
    public ModuleFormation saveModule(@RequestBody ModuleRequest request){
        return moduleService.saveModule(request);
    }
    @PostMapping("/add_client_module")
    public void saveModule(@RequestBody ValidationRequest request){
        moduleService.addModuleToUser(request.getCode(),request.getIdUser());
    }
    @GetMapping("/list_module")
    public List<ModuleFormation> listModule(){
      return  moduleService.listModule();
    }

    @GetMapping("/find_maxModule_by_code")
    public Long findByMax(){
        return  moduleService.findMaxByCode();
    }
}
