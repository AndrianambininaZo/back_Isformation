package com.fichier.fichier.service.impl;

import com.fichier.fichier.dtos.RequestPassword;
import com.fichier.fichier.dtos.UserRequest;
import com.fichier.fichier.entity.Role;
import com.fichier.fichier.entity.User;
import com.fichier.fichier.repository.ExamenRepository;
import com.fichier.fichier.repository.RoleRepository;
import com.fichier.fichier.repository.UserRepository;
import com.fichier.fichier.service.ExamenService;
import com.fichier.fichier.service.ModuleService;
import com.fichier.fichier.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExamenService examenService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModuleService moduleService;

    @Override
    public User saveUser(UserRequest request) {
        User user=new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(request.getEmail().trim());
        user.setNom(request.getNom().toUpperCase().trim());
        user.setPassword(getPasswordEncode(request.getPassword()));
        user.setStatus(1);
        user.setAdresse(request.getAdresse());
        user.setEtat(0);
        user.setTelephone(request.getTelephone());
        user.setPrenom(request.getPrenom().trim());
        User saveUser=userRepository.save(user);
        addRoleToUser(saveUser.getEmail(),"CLIENT");
        moduleService.addModuleToUser(1,saveUser.getId());
        examenService.addUserByExamen(saveUser,1L);
        return saveUser;
    }
    @Override
    public User saveUserAdmin(UserRequest request) {
        User user=new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(request.getEmail().trim());
        user.setNom(request.getNom().toUpperCase().trim());
        user.setPassword(getPasswordEncode(request.getPassword()));
        user.setStatus(1);
        user.setAdresse(request.getAdresse());
        user.setEtat(0);
        user.setTelephone(request.getTelephone());
        user.setPrenom(request.getPrenom().trim());
        User saveUser=userRepository.save(user);
        addRoleToUser(saveUser.getEmail(),"ADMIN");
        return saveUser;
    }
    @Override
    public List<User> listUser() {

        return userRepository.findAll();
    }

    @Override
    public User user(String id) {
        User user=userRepository.findById(id).get();
        return user;
    }

    @Override
    public void addRoleToUser(String email, String nomRole) {
        Role role=roleRepository.findByNomRole(nomRole);
        User user=userRepository.findByEmail(email);
        user.getRole().add(role);
    }

    @Override
    public Role saveRol(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User modifierStatus(String id) {
        User user=userRepository.findById(id).get();
        if (user.getStatus()==1){
            user.setStatus(0);
            return userRepository.save(user);
        }else {
            user.setStatus(1);
            return userRepository.save(user);
        }
    }

    @Override
    public User userByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User modifierMotDePasse(RequestPassword requestPassword) {
        User user=userRepository.findById(requestPassword.getId()).get();
        user.setPassword(getPasswordEncode(requestPassword.getSetPassword()));
        return userRepository.save(user);
    }

    @Override
    public void initialisationSuperAdmin() {
        List<Role> listRole=roleRepository.findAll();
        int coutList= listRole.size();
        if (coutList==0) {
            System.out.println("cout="+coutList);
            Role roleAdmin = new Role();
            roleAdmin.setNomRole("ADMIN");
            roleRepository.save(roleAdmin);

            Role roleClient = new Role();
            roleClient.setNomRole("CLIENT");
            roleRepository.save(roleClient);

            UserRequest user = new UserRequest();
            user.setPassword("arosaina12345");
            user.setNom("Arosaina");
            user.setEmail("arosaina@gmail.com");
            user.setStatus(1);
            user.setAdresse("Anosipatranana");
            user.setPrenom("ko");
            user.setTelephone("0340000000");
            saveUserAdmin(user);
        }
    }
    public String getPasswordEncode(String password){
        return passwordEncoder.encode(password);
    }
}
