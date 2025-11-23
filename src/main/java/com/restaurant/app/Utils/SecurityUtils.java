package com.restaurant.app.Utils;

import com.restaurant.app.User.entity.User;
import com.restaurant.app.security.jwt.JwtUserAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static Long getCompanyId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth instanceof JwtUserAuthentication jwtUserAuth){
            return jwtUserAuth.getCompanyId();
        }

        return null;
    }

    public static User getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null && auth.getPrincipal() instanceof User user){
            return user;
        }

        return null;
    }
}
