package com.fichier.fichier.web;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/demo")
@CrossOrigin
public class Demo {
    @GetMapping
    public String tets(){
        return "salut formation";
    }

}
