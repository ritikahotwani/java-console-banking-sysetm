package com.mycompany.entity;

public class accountant {
    private String accountantUsername;
    private String accountantEmail;
    private String accountantPassword;
    @Override
    public String toString() {
        return "accountant [accountantUsername=" + accountantUsername + ", accountantEmail=" + accountantEmail
                + ", accountantPassword=" + accountantPassword + "]";
    }
    public String getAccountantUsername() {
        return accountantUsername;
    }
    public void setAccountantUsername(String accountantUsername) {
        this.accountantUsername = accountantUsername;
    }
    public String getAccountantEmail() {
        return accountantEmail;
    }
    public void setAccountantEmail(String accountantEmail) {
        this.accountantEmail = accountantEmail;
    }
    public String getAccountantPassword() {
        return accountantPassword;
    }
    public void setAccountantPassword(String accountantPassword) {
        this.accountantPassword = accountantPassword;
    }
    public accountant() {
    }
    public accountant(String accountantUsername, String accountantEmail, String accountantPassword) {
        this.accountantUsername = accountantUsername;
        this.accountantEmail = accountantEmail;
        this.accountantPassword = accountantPassword;
    }
}
