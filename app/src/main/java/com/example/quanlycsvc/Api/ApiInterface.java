package com.example.quanlycsvc.Api;

import com.example.quanlycsvc.Models.Account;
import com.example.quanlycsvc.Models.DanhSachThietBiDaMuonUser;
import com.example.quanlycsvc.Models.DanhSachThietBiUser;
import com.example.quanlycsvc.Models.DonViTinh;
import com.example.quanlycsvc.Models.DroplistRole;
import com.example.quanlycsvc.Models.DroplistTrangThai;
import com.example.quanlycsvc.Models.DuyetMuonChoNguoiDung;
import com.example.quanlycsvc.Models.GetFullThietBiMuon;
import com.example.quanlycsvc.Models.GetInfoCBVC;
import com.example.quanlycsvc.Models.GoogleLoginRequest;
import com.example.quanlycsvc.Models.HuyMuonUser;
import com.example.quanlycsvc.Models.PhanLoai;
import com.example.quanlycsvc.Models.PhongHoc;
import com.example.quanlycsvc.Models.ThanhVien;
import com.example.quanlycsvc.Models.ThuongHieu;
import com.example.quanlycsvc.Models.UpdateAccount;
import com.example.quanlycsvc.Models.UpdateThietBi;
import com.example.quanlycsvc.Models.UserMuonThietBi;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    //GET
    @GET("get_full_phong_hoc")
    Call <List<PhongHoc>> getfullphonghoc();

    @GET("get_full_thiet_bi")
    Call<List<DanhSachThietBiUser>> getfullthietbi();

    @GET("get-full-thiet-bi-muon")
    Call<List<GetFullThietBiMuon>> getfullthietbimuon();

    @GET("droplist_trang_thai_duyet_muon")
    Call<List<DroplistTrangThai>> droplisttrangthai();

    @GET("get_full_account")
    Call<List<Account>> get_full_account();

    @GET("droplist_role")
    Call<List<DroplistRole>> droplistRole();

    @GET("get_full_thuong_hieu")
    Call<List<ThuongHieu>> droplistThuongHieu();

    @GET("droplist-don-vi-tinh")
    Call<List<DonViTinh>> droplistDonViTinh();

    @GET("droplist-phan-loai")
    Call<List<PhanLoai>> droplistPhanLoai();
    //POST
    @POST("get_cbvc_by_email")
    Call<GetInfoCBVC> getinfocbvc(@Body GetInfoCBVC getInfoCBVC);
    @POST("create_phong_hoc")
    Call<PhongHoc> create_phong_hoc(@Body PhongHoc createphonghoc);
    @POST("login-with-google")
    Call<GoogleLoginRequest> loginwithgoogle(@Body GoogleLoginRequest loginRequest);
    @POST("delete_phong_hoc")
    Call<PhongHoc> delete_phong_hoc (@Body PhongHoc deletephonghoc);
    @POST("user_muon_thiet_bi")
    Call<UserMuonThietBi> user_muon_thiet_bi(@Body UserMuonThietBi userMuonThietBi);

    @POST("get-full-thiet-bi-muon-by-cbvc")
    Call<GetInfoCBVC> getthietbibycbvc(@Body GetInfoCBVC getInfoCBVC);

    @POST("get-full-thiet-bi-muon-by-cbvc")
    Call<List<DanhSachThietBiDaMuonUser>> get_full_thiet_bi_user_muon(@Body GetInfoCBVC getInfoCBVC);

    @POST("user-huy-muon-thiet-bi")
    Call<HuyMuonUser> huymuonuser(@Body HuyMuonUser huyMuonUser);

    @POST("duyet-muon-user")
    Call<DuyetMuonChoNguoiDung> duyetmuonchonguoidung(@Body DuyetMuonChoNguoiDung duyetMuonChoNguoiDung);

    @POST("update-account")
    Call<UpdateAccount> update_account(@Body UpdateAccount updateAccount);

    @POST("update-thiet-bi")
    Call<UpdateThietBi> them_moi_thiet_bi (@Body UpdateThietBi updateThietBi);

    @POST("sua-thiet-bi")
    Call<UpdateThietBi> update_thiet_bi (@Body UpdateThietBi updateThietBi);
    //PUT
    @PUT("update_phong_hoc")
    Call<PhongHoc> update_phong_hoc (@Body PhongHoc updatephonghoc);
}
