package com.securebank.bank.model;

import com.securebank.bank.services.errors.ApplicationValidationError;

import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class Login {
    private String username;
    private String password;
    private String otptoken;

    public Login() {
    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
        this.otptoken = null;
    }

    public Login(String username, String password, String otptoken) {
        this.username = username;
        this.password = password;
        this.otptoken = otptoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        String password = this.password;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            password = DatatypeConverter.printHexBinary(md.digest());
        } catch (Exception e) {
            throw new ApplicationValidationError(Response.Status.UNAUTHORIZED, "hashing error 3");
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOtptoken() {
        return this.otptoken;
    }
}
