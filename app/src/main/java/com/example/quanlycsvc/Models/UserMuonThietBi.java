package com.example.quanlycsvc.Models;

public class UserMuonThietBi {
    public String email;
    public String ten_phong_hoc;
    public String ten_thiet_bi;
    public int so_luong_muon;
    public String yeu_cau;
    public String message;

    public UserMuonThietBi(String email, String ten_phong_hoc, String ten_thiet_bi, int so_luong_muon, String yeu_cau, String message) {
        this.email = email;
        this.ten_phong_hoc = ten_phong_hoc;
        this.ten_thiet_bi = ten_thiet_bi;
        this.so_luong_muon = so_luong_muon;
        this.yeu_cau = yeu_cau;
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTen_phong_hoc() {
        return ten_phong_hoc;
    }

    public void setTen_phong_hoc(String ten_phong_hoc) {
        this.ten_phong_hoc = ten_phong_hoc;
    }

    public String getTen_thiet_bi() {
        return ten_thiet_bi;
    }

    public void setTen_thiet_bi(String ten_thiet_bi) {
        this.ten_thiet_bi = ten_thiet_bi;
    }

    public int getSo_luong_muon() {
        return so_luong_muon;
    }

    public void setSo_luong_muon(int so_luong_muon) {
        this.so_luong_muon = so_luong_muon;
    }

    public String getYeu_cau() {
        return yeu_cau;
    }

    public void setYeu_cau(String yeu_cau) {
        this.yeu_cau = yeu_cau;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
