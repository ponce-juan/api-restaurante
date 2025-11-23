package com.restaurant.app.security.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtUserAuthentication extends UsernamePasswordAuthenticationToken {

    private final Long companyId;

    public JwtUserAuthentication(Object principal, Long companyId, Collection<? extends GrantedAuthority> authorities) {
        super(principal, null, authorities);
        this.companyId = companyId;
    }

    public Long getCompanyId(){
        return companyId;
    }

}
