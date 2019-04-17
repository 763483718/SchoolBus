package com.example.answer.bean;

public class Admin {
    private String adminAccount;
    private String adminPassword;
    private String adminEmail;
    private String adminTelephone;
    private String adminName;
    public Admin(){

    }
    public Admin(String account,String password,String email,String telephone,String name){
        this.adminAccount = account;
        this.adminPassword = password;
        this.adminEmail = email;
        this.adminTelephone = telephone;
        this.adminName = name;
    }

    public String getAdminAccount() {
        return adminAccount;
    }
    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }

    public String getAdminPassword() {
        return adminPassword;
    }
    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminTelephone() {
        return adminTelephone;
    }
    public void setAdminTelephone(String adminTelephone) {
        this.adminTelephone = adminTelephone;
    }

    public String getAdminEmail() {
        return adminEmail;
    }
    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminName() {
        return adminName;
    }
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
}
