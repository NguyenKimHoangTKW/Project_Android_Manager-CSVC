package com.example.quanlycsvc.Api;

import com.example.quanlycsvc.Models.Account;
import com.example.quanlycsvc.Models.DanhSachThietBiDaMuonUser;
import com.example.quanlycsvc.Models.DanhSachThietBiUser;
import com.example.quanlycsvc.Models.DroplistRole;
import com.example.quanlycsvc.Models.DroplistTrangThai;
import com.example.quanlycsvc.Models.DuyetMuonChoNguoiDung;
import com.example.quanlycsvc.Models.GetFullThietBiMuon;
import com.example.quanlycsvc.Models.GetInfoCBVC;
import com.example.quanlycsvc.Models.GoogleLoginRequest;
import com.example.quanlycsvc.Models.HuyMuonUser;
import com.example.quanlycsvc.Models.PhongHoc;
import com.example.quanlycsvc.Models.ThanhVien;
import com.example.quanlycsvc.Models.UpdateAccount;
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
    @GET("api/get_full_phong_hoc")
    Call <List<PhongHoc>> getfullphonghoc();

    @GET("api/get_full_thiet_bi")
    Call<List<DanhSachThietBiUser>> getfullthietbi();

    @GET("api/get-full-thiet-bi-muon")
    Call<List<GetFullThietBiMuon>> getfullthietbimuon();

    @GET("api/droplist_trang_thai_duyet_muon")
    Call<List<DroplistTrangThai>> droplisttrangthai();

    @GET("api/get_full_account")
    Call<List<Account>> get_full_account();

    @GET("api/droplist_role")
    Call<List<DroplistRole>> droplistRole();
    //POST
    @POST("api/get_cbvc_by_email")
    Call<GetInfoCBVC> getinfocbvc(@Body GetInfoCBVC getInfoCBVC);
    @POST("api/create_phong_hoc")
    Call<PhongHoc> create_phong_hoc(@Body PhongHoc createphonghoc);
    @POST("api/login-with-google")
    Call<GoogleLoginRequest> loginwithgoogle(@Body GoogleLoginRequest loginRequest);
    @POST("api/delete_phong_hoc")
    Call<PhongHoc> delete_phong_hoc (@Body PhongHoc deletephonghoc);
    @POST("api/user_muon_thiet_bi")
    Call<UserMuonThietBi> user_muon_thiet_bi(@Body UserMuonThietBi userMuonThietBi);

    @POST("api/get-full-thiet-bi-muon-by-cbvc")
    Call<GetInfoCBVC> getthietbibycbvc(@Body GetInfoCBVC getInfoCBVC);

    @POST("api/get-full-thiet-bi-muon-by-cbvc")
    Call<List<DanhSachThietBiDaMuonUser>> get_full_thiet_bi_user_muon(@Body GetInfoCBVC getInfoCBVC);

    @POST("api/user-huy-muon-thiet-bi")
    Call<HuyMuonUser> huymuonuser(@Body HuyMuonUser huyMuonUser);

    @POST("api/duyet-muon-user")
    Call<DuyetMuonChoNguoiDung> duyetmuonchonguoidung(@Body DuyetMuonChoNguoiDung duyetMuonChoNguoiDung);

    @POST("api/update-account")
    Call<UpdateAccount> update_account(@Body UpdateAccount updateAccount);
    //PUT
    @PUT("api/update_phong_hoc")
    Call<PhongHoc> update_phong_hoc (@Body PhongHoc updatephonghoc);


}
