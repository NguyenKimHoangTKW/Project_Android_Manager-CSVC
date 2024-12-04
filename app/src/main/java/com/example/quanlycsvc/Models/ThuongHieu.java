package com.example.quanlycsvc.Models;

public class ThuongHieu {
    public int id_thuong_hieu;
    public String ten_thuong_hieu;

    public ThuongHieu(int id_thuong_hieu, String ten_thuong_hieu) {
        this.id_thuong_hieu = id_thuong_hieu;
        this.ten_thuong_hieu = ten_thuong_hieu;
    }

    public int getId_thuong_hieu() {
        return id_thuong_hieu;
    }

    public void setId_thuong_hieu(int id_thuong_hieu) {
        this.id_thuong_hieu = id_thuong_hieu;
    }

    public String getTen_thuong_hieu() {
        return ten_thuong_hieu;
    }

    public void setTen_thuong_hieu(String ten_thuong_hieu) {
        this.ten_thuong_hieu = ten_thuong_hieu;
    }
}
