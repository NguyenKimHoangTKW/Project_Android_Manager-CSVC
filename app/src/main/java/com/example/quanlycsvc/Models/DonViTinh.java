package com.example.quanlycsvc.Models;

public class DonViTinh {
    public int id_don_vi_tinh;
    public String ten_don_vi_tinh;

    public DonViTinh(int id_don_vi_tinh, String ten_don_vi_tinh) {
        this.id_don_vi_tinh = id_don_vi_tinh;
        this.ten_don_vi_tinh = ten_don_vi_tinh;
    }

    public int getId_don_vi_tinh() {
        return id_don_vi_tinh;
    }

    public void setId_don_vi_tinh(int id_don_vi_tinh) {
        this.id_don_vi_tinh = id_don_vi_tinh;
    }

    public String getTen_don_vi_tinh() {
        return ten_don_vi_tinh;
    }

    public void setTen_don_vi_tinh(String ten_don_vi_tinh) {
        this.ten_don_vi_tinh = ten_don_vi_tinh;
    }
}
