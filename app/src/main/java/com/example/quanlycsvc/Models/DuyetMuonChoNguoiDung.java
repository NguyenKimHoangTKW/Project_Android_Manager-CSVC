package com.example.quanlycsvc.Models;

public class DuyetMuonChoNguoiDung {
    public int id_danh_sach_muon;
    public String ten_thiet_bi;
    public String ten_trang_thai;
    public String ly_do_huy;
    public String message;

    public DuyetMuonChoNguoiDung(int id_danh_sach_muon, String ten_thiet_bi, String ten_trang_thai, String ly_do_huy, String message) {
        this.id_danh_sach_muon = id_danh_sach_muon;
        this.ten_thiet_bi = ten_thiet_bi;
        this.ten_trang_thai = ten_trang_thai;
        this.ly_do_huy = ly_do_huy;
        this.message = message;
    }

    public int getId_danh_sach_muon() {
        return id_danh_sach_muon;
    }

    public void setId_danh_sach_muon(int id_danh_sach_muon) {
        this.id_danh_sach_muon = id_danh_sach_muon;
    }

    public String getTen_thiet_bi() {
        return ten_thiet_bi;
    }

    public void setTen_thiet_bi(String ten_thiet_bi) {
        this.ten_thiet_bi = ten_thiet_bi;
    }

    public String getTen_trang_thai() {
        return ten_trang_thai;
    }

    public void setTen_trang_thai(String ten_trang_thai) {
        this.ten_trang_thai = ten_trang_thai;
    }

    public String getLy_do_huy() {
        return ly_do_huy;
    }

    public void setLy_do_huy(String ly_do_huy) {
        this.ly_do_huy = ly_do_huy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
