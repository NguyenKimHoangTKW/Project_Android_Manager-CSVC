package com.example.quanlycsvc.Models;

public class DroplistRole {
    public int id_role;
    public String ten_role;

    public DroplistRole(int id_role, String ten_role) {
        this.id_role = id_role;
        this.ten_role = ten_role;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public String getTen_role() {
        return ten_role;
    }

    public void setTen_role(String ten_role) {
        this.ten_role = ten_role;
    }
}
