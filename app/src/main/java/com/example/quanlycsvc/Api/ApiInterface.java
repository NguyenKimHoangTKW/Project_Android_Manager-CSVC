package com.example.quanlycsvc.Api;

import com.example.quanlycsvc.Models.ThanhVien;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("api/load_full_thanh_vien")
    Call <List<ThanhVien>> getFullThanhVien();

    @PUT("api/update_group/{id}")
    Call<Void> UpdateGroup(@Path("id") int id, @Body Object body);
}
