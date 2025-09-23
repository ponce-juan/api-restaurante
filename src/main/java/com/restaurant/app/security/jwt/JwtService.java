package com.restaurant.app.security.jwt;

import com.restaurant.app.User.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private Long jwtExpiration;

    //genero el token con el rol, username y employeeId
    public String generateToken(User user){
        Long now = System.currentTimeMillis();
        Map<String, Object> claims = Map.of(
                "role", user.getEmployee().getRole() ,
                "username", user.getUsername(),
                "employeeId", user.getEmployee().getId(),
                "companyId", user.getCompany().getId()
        );
//        Para desarrollo
//        Map<String, Object> claims = Map.of(
//                "role", "admin" ,
//                "username", user.getUsername(),
//                "employeeId", 0
//        );
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtExpiration))
                .addClaims(claims)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, User user){
        //Puntos a verificar
        //firma valida, expiracion, id asignado al mismo username
        try {
            Claims claims = extractAllClaims(token);
            //Valido expiracion, id, username y role
            return
                !isTokenExpired(claims.getExpiration()) &&
                isUserIdValid(claims, user) &&
                isUsernameValid(claims, user) &&
                isRoleValid(claims, user) &&
                isCompanyIdValid(claims, user);

        }catch (JwtException e){
            return false;
        }
    }

    //Obtengo los claims del token
    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //Genero keybytes para la clave de cifrado con HS256
    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private boolean isUserIdValid(Claims claims, User user){
        if(user != null)
            return claims.getSubject().equals(user.getId().toString());

        return false;
    }

    private boolean isUsernameValid(Claims claims, User user){
        if(user != null)
            return claims.get("username", String.class).equals(user.getUsername());

        return false;
    }

    private boolean isRoleValid(Claims claims, User user){
        if(user != null)
            return claims.get("role", String.class).equals(user.getEmployee().getRole()); //Para produccion
//          return claims.get("role", String.class).equals("admin"); //Para desarrollo
        return false;
    }

    private boolean isCompanyIdValid(Claims claims, User user){
        if(user != null && user.getCompany() != null){
            Long companyId = claims.get("companyId", Long.class);
            Long userCompanyId = user.getCompany().getId();

            return Objects.equals(companyId, userCompanyId);
        }
        return false;
    }

    private boolean isTokenExpired(Date expiration){
        return expiration.before(new Date());
    }

}
