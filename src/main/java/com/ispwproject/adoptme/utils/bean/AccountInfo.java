package com.ispwproject.adoptme.utils.bean;

public class AccountInfo {
    private String username;
    private String password;
    private String email;
    private int accountType;

    public AccountInfo(String username, String password, String email, int accountType) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.accountType = accountType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }
}
