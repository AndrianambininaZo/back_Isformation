package com.fichier.fichier.service;

import com.fichier.fichier.dtos.JwtRequest;
import com.fichier.fichier.dtos.JwtResponse;
import com.fichier.fichier.entity.User;
import com.fichier.fichier.repository.UserRepository;
import com.fichier.fichier.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JwtService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
   @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    public JwtResponse createJwtToken(JwtRequest request) throws Exception {
        String username=request.getUsername();
        String password=request.getPassword();
        authenticate(username,password);
      final UserDetails userDetails= loadUserByUsername(username);
      String newGenerateToken=jwtUtil.generateToken(userDetails);
      User user1=userRepository.findByEmail(username);
      return new JwtResponse(user1,newGenerateToken);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(username);
        if (user !=null){
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    getAuthorities(user)
            );
        }else {
            throw new UsernameNotFoundException("username is not invalid");
        }
    }

    private Set getAuthorities(User user){
        Set authorities=new HashSet<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getNomRole()));
        });
        return authorities;
    }
    private void authenticate(String username,String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,userPassword));
        }catch (DisabledException e){
            throw new Exception("user is disabled");
        }catch (BadCredentialsException e){
            throw  new Exception("Bad credentials user");
        }

    }
}
