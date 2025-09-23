package com.restaurant.app.security.jwt;

import com.restaurant.app.User.entity.User;
import com.restaurant.app.User.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final HandlerExceptionResolver handlerExceptionResolver;

    private static final String HEADER_AUTH = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(
                    HttpServletRequest request,
                    HttpServletResponse response,
                    FilterChain filterChain) throws ServletException, IOException
    {
        final String authHeader = request.getHeader(HEADER_AUTH);
        //Valido que el token empiece con "Bearer "
        if(authHeader != null && authHeader.startsWith(BEARER_PREFIX)){
            final String token = authHeader.substring(BEARER_PREFIX.length()); ///Extraigo el prefijo Bearer

            try{
                String userId = jwtService.extractAllClaims(token).getSubject();
                Long companyId = jwtService.extractAllClaims(token).get("companyId", Long.class);
                //Verifico si ya fue autenticado
                if(userId != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    User user = userRepository.findById(Long.valueOf(userId)).orElse(null);
                    //Valido el token obtenido del header
                    if(user != null && jwtService.isTokenValid(token, user)){
                        String role = user.getEmployee().getRole();
//                      String role = "admin"; // Para desarrollo
                        //Creo el objeto de autenticacion
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                user, null,
                                List.of(new SimpleGrantedAuthority("ROLE_"+role))
                                );

                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        //Seteo la autenticacion en el SecurityContext de spring
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }


            } catch (Exception exception) {
//                handlerExceptionResolver.resolveException(request, response, null, exception);
                System.out.println("Excepcion JwtAuthenticationFilter");
                SecurityContextHolder.clearContext();
            }
        }
        //Continua el filtro
        filterChain.doFilter(request, response);
    }


}
