package com.travanleo.auth.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneratePassword {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder4 = new BCryptPasswordEncoder(4);
        /*
         * Users: john/john@123 kelly/kelly@123
         *
         * Generated Bcrypt password will be used in data.sql (users table)
         */

        System.out.println("John's Bcrypt encrypted password = " + encoder4.encode("john@123"));
        System.out.println("kelly's Bcrypt encrypted password = " + encoder4.encode("kelly@123"));

        System.out.println("app client's Bcrypt encrypted password = "
                + encoder4.encode("appclient@123"));
        System.out.println("app user service client's Bcrypt encrypted password = "
                + encoder4.encode("appuserclient@123"));
        System.out.println("app comment service client's Bcrypt encrypted password = "
                + encoder4.encode("appcommentclient@123"));
    }
}
