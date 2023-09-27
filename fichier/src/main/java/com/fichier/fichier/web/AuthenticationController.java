package com.fichier.fichier.web;

import com.fichier.fichier.dtos.JwtRequest;
import com.fichier.fichier.dtos.JwtResponse;
import com.fichier.fichier.service.JwtService;
import com.fichier.fichier.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;


@RestController
@CrossOrigin
public class AuthenticationController {
    @Autowired
    private UserService service;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/connexion")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        if (jwtRequest.getCodeEntree().equals("isClient")){
            return jwtService.createJwtToken(jwtRequest);
        }
        else {
            return null;
        }
    }
    @PostMapping("/authentication")
    public Object createJwtTokenFormAdmin(@RequestBody JwtRequest jwtRequest) throws Exception {
        if (jwtRequest.getCodeEntree().equals("isBackAndAdmin")){
            return jwtService.createJwtToken(jwtRequest);
        }
        else {
            return null;
        }
    }
    @PostMapping("/verificationIsPassword")
    public Object createJwtTokenFormA(@RequestBody JwtRequest jwtRequest) throws Exception {
        if (jwtRequest.getCodeEntree().equals("isPassword")){
            return jwtService.createJwtToken(jwtRequest);
        }
        else {
            return null;
        }
    }
    @PostConstruct
    public void initialisationAdmin(){
        service.initialisationSuperAdmin();
    }
}
