package com.example.quanlycsvc.Models;

public class PhongHoc {
    public int id_phong_hoc;
    public String ten_phong_hoc;
    public String message;

    public PhongHoc(int id_phong_hoc, String ten_phong_hoc, String message) {
        this.id_phong_hoc = id_phong_hoc;
        this.ten_phong_hoc = ten_phong_hoc;
        this.message = message;
    }

    public int getId_phong_hoc() {
        return id_phong_hoc;
    }

    public void setId_phong_hoc(int id_phong_hoc) {
        this.id_phong_hoc = id_phong_hoc;
    }

    public String getTen_phong_hoc() {
        return ten_phong_hoc;
    }

    public void setTen_phong_hoc(String ten_phong_hoc) {
        this.ten_phong_hoc = ten_phong_hoc;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
