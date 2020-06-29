package com.travanleo.auth.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneratePassword {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder4 = new BCryptPasswordEncoder(4);
        System.out.println("app user service client's Bcrypt encrypted password = "
                + encoder4.encode("appuserclient@123"));
        System.out.println("app comment service client's Bcrypt encrypted password = "
                + encoder4.encode("appcommentclient@123"));
    }
}
