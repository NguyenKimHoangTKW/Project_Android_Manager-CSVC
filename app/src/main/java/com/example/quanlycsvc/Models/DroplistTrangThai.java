package com.example.quanlycsvc.Models;

public class DroplistTrangThai {
    public int id_trang_thai;
    public String ten_trang_thaii;

    public DroplistTrangThai(int id_trang_thai, String ten_trang_thaii) {
        this.id_trang_thai = id_trang_thai;
        this.ten_trang_thaii = ten_trang_thaii;
    }

    public int getId_trang_thai() {
        return id_trang_thai;
    }

    public void setId_trang_thai(int id_trang_thai) {
        this.id_trang_thai = id_trang_thai;
    }

    public String getTen_trang_thaii() {
        return ten_trang_thaii;
    }

    public void setTen_trang_thaii(String ten_trang_thaii) {
        this.ten_trang_thaii = ten_trang_thaii;
    }
}
