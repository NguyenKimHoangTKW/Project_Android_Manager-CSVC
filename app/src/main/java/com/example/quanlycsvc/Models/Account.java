package com.example.quanlycsvc.Models;

public class Account {
    public int id_account;
    public String name;
    public String email;
    public String ten_role;

    public Account(int id_account, String name, String email, String ten_role) {
        this.id_account = id_account;
        this.name = name;
        this.email = email;
        this.ten_role = ten_role;
    }

    public int getId_account() {
        return id_account;
    }

    public void setId_account(int id_account) {
        this.id_account = id_account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTen_role() {
        return ten_role;
    }

    public void setTen_role(String ten_role) {
        this.ten_role = ten_role;
    }
}
