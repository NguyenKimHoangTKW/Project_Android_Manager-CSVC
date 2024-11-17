package com.example.quanlycsvc.Models;

public class ThanhVien {
    public int id_thanh_vien;
    public String ten_thanh_vien;

    public ThanhVien(int id_thanh_vien, String ten_thanh_vien) {
        this.id_thanh_vien = id_thanh_vien;
        this.ten_thanh_vien = ten_thanh_vien;
    }

    public int getId_thanh_vien() {
        return id_thanh_vien;
    }

    public void setId_thanh_vien(int id_thanh_vien) {
        this.id_thanh_vien = id_thanh_vien;
    }

    public String getTen_thanh_vien() {
        return ten_thanh_vien;
    }

    public void setTen_thanh_vien(String ten_thanh_vien) {
        this.ten_thanh_vien = ten_thanh_vien;
    }
}
