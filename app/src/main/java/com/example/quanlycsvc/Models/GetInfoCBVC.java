package com.example.quanlycsvc.Models;

public class GetInfoCBVC {
    public String ten_cbvc;
    public String email;

    public GetInfoCBVC(String ten_cbvc, String email) {
        this.ten_cbvc = ten_cbvc;
        this.email = email;
    }

    public String getTen_cbvc() {
        return ten_cbvc;
    }

    public void setTen_cbvc(String ten_cbvc) {
        this.ten_cbvc = ten_cbvc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
