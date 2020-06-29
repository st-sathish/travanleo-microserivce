package com.travanleo.auth.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Base64;

public class CodeachesBase64Encoder {

    public static void main(String[] args) {
        // Get the Base64 password for appclient:appuserclient@123
        String base64AuthHeader = Base64.getEncoder().encodeToString("appclient:appuserclient@123".getBytes());

        // This Base64 password for appclient:appuserclient@123 will be used in the http
        // header when requesting the token
        System.out.println("app user client's Base64 Password is " + base64AuthHeader);

        // Get the Base64 password for appclient:appcommentclient@123
        String base64AuthHeader1 = Base64.getEncoder().encodeToString("appclient:appcommentclient@123".getBytes());

        // This Base64 password for appclient:appcommentclient@123 will be used in the http
        // header when requesting the token
        System.out.println("app comment client's Base64 Password is " + base64AuthHeader1);
    }
}
