package com.ispwproject.adoptme.utils.bean;

public class AccountInfo {
    private String password;
    private String email;
    private int accountType; // 0 -> USER  |  1 -> SHELTER
    private int gmailVerified; // 0 -> no Gmail | 1 -> Gmail

    public AccountInfo(String email,String password, int accountType) {
        this.email = email;
        this.password = password;
        this.accountType = accountType;
    }

    public AccountInfo(String email, int accountType) {
        this.email = email;
        this.accountType = accountType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {}

    public int getGmailVerified() {
        return gmailVerified;
    }

    public void setGmailVerified() {
        if(email.contains("gmail.com"))
            this.gmailVerified = 1;
        else
            this.gmailVerified = 0;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }
}
