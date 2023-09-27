package com.fichier.fichier.web;

import com.fichier.fichier.dtos.ExamenRequest;
import com.fichier.fichier.entity.Examen;
import com.fichier.fichier.service.ExamenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ExamenController {
    private ExamenService examenService;
    @PostMapping("/save_examen")
    public Examen saveExamen(@RequestBody ExamenRequest request){
        return examenService.saveExamen(request);
    }
    @GetMapping("/list_examen")
    public List<Examen> listExamen(){
        return examenService.listExamen();
    }

    @GetMapping("/list_examen_by_client/{idClient}")
    public List<Examen> listExamenByClient(@PathVariable("idClient") String idClient){
        return examenService.listExamenFindBy(idClient);
    }
}
