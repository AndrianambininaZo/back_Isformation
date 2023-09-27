package com.fichier.fichier.web;

import com.fichier.fichier.dtos.FileByModuleRequest;
import com.fichier.fichier.entity.FileByModule;
import com.fichier.fichier.repository.FileByModuleRepository;
import com.fichier.fichier.service.FileByModuleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class FileByModuleController {
    private FileByModuleService fileByModuleService;
    @PostMapping("/save_file_by_module")
    public FileByModule saveFileByModule(@RequestBody FileByModuleRequest request){
        return fileByModuleService.saveFileByModule(request);
    }
    @GetMapping("/file_by_module/{idModule}")
    public List<FileByModule> listFileByModule(@PathVariable("idModule")Long idModule){
        return fileByModuleService.listFileByModule(idModule);
    }
    @GetMapping("/file_by_idModule/{idModule}")
    public FileByModule fileByModule(@PathVariable("idModule")Long idModule){
        return fileByModuleService.fileByModule(idModule);
    }
}
