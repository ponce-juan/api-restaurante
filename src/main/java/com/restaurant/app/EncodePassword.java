package com.restaurant.app;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncodePassword {
    public static void main(String[] args){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String clave = "mozo2";
        String encode = encoder.encode(clave);
        System.out.println("Clave: " + clave);
        System.out.println("Hash: " + encode);
    }
}
