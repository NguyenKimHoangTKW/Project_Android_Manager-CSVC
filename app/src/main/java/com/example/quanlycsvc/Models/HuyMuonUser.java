package com.example.quanlycsvc.Models;

public class HuyMuonUser {
    public int id_danh_sach_muon;
    public String ly_do_huy;
    public String message;

    public HuyMuonUser(int id_danh_sach_muon, String ly_do_huy, String message) {
        this.id_danh_sach_muon = id_danh_sach_muon;
        this.ly_do_huy = ly_do_huy;
        this.message = message;
    }

    public int getId_danh_sach_muon() {
        return id_danh_sach_muon;
    }

    public void setId_danh_sach_muon(int id_danh_sach_muon) {
        this.id_danh_sach_muon = id_danh_sach_muon;
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
