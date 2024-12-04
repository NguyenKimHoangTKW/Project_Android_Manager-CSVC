package com.example.quanlycsvc.Models;

public class UpdateThietBi {
    public Integer id_thiet_bi;
    public String ten_thiet_bi;
    public String thong_so;
    public String ten_thuong_hieu;
    public int so_luong;
    public String mo_ta;
    public String ten_don_vi_tinh;
    public String ten_phan_loai;
    public String message;

    public UpdateThietBi(int id_thiet_bi, String ten_thiet_bi, String thong_so, String ten_thuong_hieu, int so_luong, String mo_ta, String ten_don_vi_tinh, String ten_phan_loai, String message) {
        this.id_thiet_bi = id_thiet_bi;
        this.ten_thiet_bi = ten_thiet_bi;
        this.thong_so = thong_so;
        this.ten_thuong_hieu = ten_thuong_hieu;
        this.so_luong = so_luong;
        this.mo_ta = mo_ta;
        this.ten_don_vi_tinh = ten_don_vi_tinh;
        this.ten_phan_loai = ten_phan_loai;
        this.message = message;
    }

    public int getId_thiet_bi() {
        return id_thiet_bi;
    }

    public void setId_thiet_bi(int id_thiet_bi) {
        this.id_thiet_bi = id_thiet_bi;
    }

    public String getTen_thiet_bi() {
        return ten_thiet_bi;
    }

    public void setTen_thiet_bi(String ten_thiet_bi) {
        this.ten_thiet_bi = ten_thiet_bi;
    }

    public String getThong_so() {
        return thong_so;
    }

    public void setThong_so(String thong_so) {
        this.thong_so = thong_so;
    }

    public String getTen_thuong_hieu() {
        return ten_thuong_hieu;
    }

    public void setTen_thuong_hieu(String ten_thuong_hieu) {
        this.ten_thuong_hieu = ten_thuong_hieu;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

    public String getMo_ta() {
        return mo_ta;
    }

    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }

    public String getTen_don_vi_tinh() {
        return ten_don_vi_tinh;
    }

    public void setTen_don_vi_tinh(String ten_don_vi_tinh) {
        this.ten_don_vi_tinh = ten_don_vi_tinh;
    }

    public String getTen_phan_loai() {
        return ten_phan_loai;
    }

    public void setTen_phan_loai(String ten_phan_loai) {
        this.ten_phan_loai = ten_phan_loai;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
