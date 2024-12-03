package com.example.quanlycsvc.Models;

public class DanhSachThietBiDaMuonUser {
    public int id_danh_sach_muon;
    public String email;
    public String ten_phong_hoc;
    public String ten_thiet_bi;
    public int so_luong_muon;
    public String yeu_cau;
    public String ten_trang_thaii;
    public String ly_do_huy;
    public Integer ngay_dang_ky_muon;
    public Integer ngay_huy;
    public Integer ngay_muon;
    public Integer ngay_tra;
    public String message;

    public DanhSachThietBiDaMuonUser(int id_danh_sach_muon, String email, String ten_phong_hoc, String ten_thiet_bi, int so_luong_muon, String yeu_cau, String ten_trang_thaii, String ly_do_huy, Integer ngay_dang_ky_muon, Integer ngay_huy, Integer ngay_muon, Integer ngay_tra, String message) {
        this.id_danh_sach_muon = id_danh_sach_muon;
        this.email = email;
        this.ten_phong_hoc = ten_phong_hoc;
        this.ten_thiet_bi = ten_thiet_bi;
        this.so_luong_muon = so_luong_muon;
        this.yeu_cau = yeu_cau;
        this.ten_trang_thaii = ten_trang_thaii;
        this.ly_do_huy = ly_do_huy;
        this.ngay_dang_ky_muon = ngay_dang_ky_muon;
        this.ngay_huy = ngay_huy;
        this.ngay_muon = ngay_muon;
        this.ngay_tra = ngay_tra;
        this.message = message;
    }

    public int getId_danh_sach_muon() {
        return id_danh_sach_muon;
    }

    public void setId_danh_sach_muon(int id_danh_sach_muon) {
        this.id_danh_sach_muon = id_danh_sach_muon;
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

    public String getTen_trang_thaii() {
        return ten_trang_thaii;
    }

    public void setTen_trang_thaii(String ten_trang_thaii) {
        this.ten_trang_thaii = ten_trang_thaii;
    }

    public String getLy_do_huy() {
        return ly_do_huy;
    }

    public void setLy_do_huy(String ly_do_huy) {
        this.ly_do_huy = ly_do_huy;
    }

    public Integer getNgay_dang_ky_muon() {
        return ngay_dang_ky_muon;
    }

    public void setNgay_dang_ky_muon(Integer ngay_dang_ky_muon) {
        this.ngay_dang_ky_muon = ngay_dang_ky_muon;
    }

    public Integer getNgay_huy() {
        return ngay_huy;
    }

    public void setNgay_huy(Integer ngay_huy) {
        this.ngay_huy = ngay_huy;
    }

    public Integer getNgay_muon() {
        return ngay_muon;
    }

    public void setNgay_muon(Integer ngay_muon) {
        this.ngay_muon = ngay_muon;
    }

    public Integer getNgay_tra() {
        return ngay_tra;
    }

    public void setNgay_tra(Integer ngay_tra) {
        this.ngay_tra = ngay_tra;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
