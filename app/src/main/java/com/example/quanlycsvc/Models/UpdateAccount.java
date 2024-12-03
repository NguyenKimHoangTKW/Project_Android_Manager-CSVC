package com.example.quanlycsvc.Models;

public class UpdateAccount {
    public int id_account;
    public String ten_role;
    public String message;

    public UpdateAccount(int id_account, String ten_role, String message) {
        this.id_account = id_account;
        this.ten_role = ten_role;
        this.message = message;
    }

    public int getId_account() {
        return id_account;
    }

    public void setId_account(int id_account) {
        this.id_account = id_account;
    }

    public String getTen_role() {
        return ten_role;
    }

    public void setTen_role(String ten_role) {
        this.ten_role = ten_role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
