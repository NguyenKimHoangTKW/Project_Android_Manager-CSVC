package com.example.quanlycsvc.Models;

public class PhanLoai {
    public int id_phan_loai;
    public String ten_phan_loai;

    public PhanLoai(int id_phan_loai, String ten_phan_loai) {
        this.id_phan_loai = id_phan_loai;
        this.ten_phan_loai = ten_phan_loai;
    }

    public int getId_phan_loai() {
        return id_phan_loai;
    }

    public void setId_phan_loai(int id_phan_loai) {
        this.id_phan_loai = id_phan_loai;
    }

    public String getTen_phan_loai() {
        return ten_phan_loai;
    }

    public void setTen_phan_loai(String ten_phan_loai) {
        this.ten_phan_loai = ten_phan_loai;
    }
}
