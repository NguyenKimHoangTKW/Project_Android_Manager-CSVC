package com.example.quanlycsvc.ActivityInterface;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quanlycsvc.Api.RetrofitInstance;
import com.example.quanlycsvc.Models.PhongHoc;
import com.example.quanlycsvc.Models.ThanhVien;
import com.example.quanlycsvc.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhongHocActivity extends AppCompatActivity {
    List<PhongHoc> phongHocList;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView tvname,tvemail;
    Button btnSignOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_phong_hoc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvname = findViewById(R.id.tvNameAns);
        tvemail = findViewById(R.id.tvEmailAns);
        btnSignOut = findViewById(R.id.btnLogout);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if(acct != null){
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            tvname.setText(personName);
            tvemail.setText(personEmail);
        }
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignOut();
            }
        });
        GetFullPhongHoc();
    }

    private void SignOut() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                startActivity(new Intent(PhongHocActivity.this,MainActivity.class));
            }
        });
    }


    public void GetFullPhongHoc(){
        RetrofitInstance.getRetrofitInstance().apiInterface.getfullphonghoc().enqueue(new Callback<List<PhongHoc>>() {
            @Override
            public void onResponse(Call<List<PhongHoc>> call, Response<List<PhongHoc>> response) {
                phongHocList = response.body();
                for (PhongHoc ph : phongHocList){
                    Log.e("Message","onResponse:"+ph.ten_phong_hoc);
                }
            }

            @Override
            public void onFailure(Call<List<PhongHoc>> call, Throwable t) {
                Log.e("api","OnFailure:" + t.getLocalizedMessage());
            }
        });
    }

}