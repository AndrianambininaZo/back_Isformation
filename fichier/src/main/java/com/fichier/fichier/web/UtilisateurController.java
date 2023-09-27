package com.fichier.fichier.web;

import com.fichier.fichier.dtos.FormRoleToUser;
import com.fichier.fichier.dtos.RequestPassword;
import com.fichier.fichier.dtos.UserRequest;
import com.fichier.fichier.entity.User;
import com.fichier.fichier.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class UtilisateurController {
    private UserService service;
    @GetMapping("/getu_utilisateurs")
    public List<User> list(){
        return service.listUser();
    }
    @GetMapping("/get_utilisateur/{id}")
    public User user(@PathVariable("id") String id){
        return service.user(id);
    }
    @GetMapping("/utilisateur_email/{email}")
    public User userByEmail(@PathVariable("email") String email){
        return service.userByEmail(email);
    }
    @PostMapping("/utilisateur")
    public User enregistreUser(@RequestBody UserRequest user){

        return service.saveUser(user);
    }
    @PostMapping("/utilisateurAdmi")
    public User enregistreUserAdmin(@RequestBody UserRequest user){

        return service.saveUserAdmin(user);
    }
    @GetMapping("/utilisateur/setStatus")
    public ResponseEntity<User>  enregistreUser(@Param("id") String id){
        return ResponseEntity.ok().body(service.modifierStatus(id));
    }
    @PostMapping("/utilisateur/setPassword")
    public User  setPassword(@RequestBody RequestPassword requestPassword){
        return service.modifierMotDePasse(requestPassword);
    }
    @PostMapping("addRole")
    public void addRoleToUser(@RequestBody FormRoleToUser formRoleToUser){
        service.addRoleToUser(formRoleToUser.getEmail(),formRoleToUser.getRole());
    }
}
