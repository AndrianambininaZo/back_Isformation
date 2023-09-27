package com.fichier.fichier.util;

import com.fichier.fichier.entity.Role;
import com.fichier.fichier.entity.User;
import com.fichier.fichier.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    @Autowired
    private UserRepository userRepository;
    private static final String SECRET_KEY="traitemmentfichier";
    private static final int TOKEN_VALIDITY=3600 * 5;
    public String getUserNameFromToken(String token){
        return getClaimsFromToken(token,Claims::getSubject);
    };
    private <T> T getClaimsFromToken(String token, Function<Claims,T> claimsResolver){
      final Claims claims= getAllClaimsFromToke(token);
      return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToke(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
    public boolean validateToken(String token, UserDetails userDetails){
        String username=getUserNameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public boolean isTokenExpired(String token){
        final Date expirationDate=getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }
    private Date getExpirationDateFromToken(String token){
        return getClaimsFromToken(token,Claims::getExpiration);
    }
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims=new HashMap<>();
        User user=userRepository.findByEmail(userDetails.getUsername());
        List<Role> roles=user.getRole();
        Role getRole= roles.get(0);
        String role=getRole.getNomRole();
        if (role.equals("ADMIN")){
            return Jwts.builder()
                    .setClaims(claims)
                    .claim("roles",userDetails.getAuthorities().stream().map(gra -> gra.getAuthority()).collect(Collectors.toList()))
                    .setSubject(userDetails.getUsername())
                    .claim("string",user.getId())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()+ TOKEN_VALIDITY * 1000))
                    .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                    .setIssuer("is_formation_v1_v123412345")
                    .compact();

        }else {
            return Jwts.builder()
                    .setClaims(claims)
                    .claim("roles",userDetails.getAuthorities().stream().map(gra -> gra.getAuthority()).collect(Collectors.toList()))
                    .setSubject(userDetails.getUsername())
                    .claim("string",user.getId())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()+ TOKEN_VALIDITY * 1000))
                    .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                    .setIssuer("is_formation_v1_v1234")
                    .compact();
        }

    }
}
