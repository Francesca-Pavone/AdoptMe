package com.ispwproject.adoptme.engineering.bean;

import com.ispwproject.adoptme.engineering.exception.EmailFormatException;

import java.util.regex.Pattern;

public class LoginBean {
    private String password;
    private String email;
    private int accountType; // 0 -> USER  |  1 -> SHELTER

    public LoginBean(String email, String password) throws EmailFormatException {
        this.setEmail(email);
        this.setPassword(password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    //controllo sintattico sulla correttezza dell'email
    public void setEmail(String email) throws EmailFormatException {
        String emailRegex = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
        if (!Pattern.compile(emailRegex).matcher(email).matches())
            throw new EmailFormatException(email);
        this.email = email;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }
}
