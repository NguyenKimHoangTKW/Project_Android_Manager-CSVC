package com.example.quanlycsvc.ActivityInterface;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quanlycsvc.Api.RetrofitInstance;
import com.example.quanlycsvc.Models.ThanhVien;
import com.example.quanlycsvc.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<ThanhVien> thanhVienList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RetrofitInstance.getRetrofitInstance().apiInterface.getFullThanhVien().enqueue((new Callback<List<ThanhVien>>() {
            @Override
            public void onResponse(Call<List<ThanhVien>> call, Response<List<ThanhVien>> response) {
                thanhVienList = response.body();
                for(ThanhVien tv : thanhVienList){
                    Log.e("api","onResponse:"+tv.getTen_thanh_vien());
                }
            }

            @Override
            public void onFailure(Call<List<ThanhVien>> call, Throwable t) {
                Log.e("api","OnFailure:" + t.getLocalizedMessage());
            }
        }));
    }
}