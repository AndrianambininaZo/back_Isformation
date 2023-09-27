package com.fichier.fichier.dtos;

import com.fichier.fichier.entity.Role;
import com.fichier.fichier.entity.User;

import java.util.List;

public class JwtResponse {
    private String role;
    private String jwtToken;
    public JwtResponse(User user, String jwtToken) {
        List<Role> getRole=user.getRole();

        this.role = getRole.get(0).getNomRole();;
        this.jwtToken = jwtToken;
    }

    public String  getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
