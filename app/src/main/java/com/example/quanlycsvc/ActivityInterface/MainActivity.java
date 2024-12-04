package com.example.quanlycsvc.ActivityInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quanlycsvc.ActivityInterface.KiemDuyetActivity.DanhSachThietBiMuonAllUserActivity;
import com.example.quanlycsvc.ActivityInterface.UserActivity.DanhSachThietBiMuonActivity;
import com.example.quanlycsvc.Api.RetrofitInstance;
import com.example.quanlycsvc.Models.GoogleLoginRequest;
import com.example.quanlycsvc.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    Button googleBtn;
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

        googleBtn = findViewById(R.id.btnLogin);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this,gso);

        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    private void signIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    GoogleLoginRequest loginRequest = new GoogleLoginRequest(
                            account.getDisplayName(),
                            account.getEmail(),
                            0,
                            ""
                    );
                    sendLoginApi(loginRequest);
                }
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void sendLoginApi(GoogleLoginRequest account) {
        GoogleLoginRequest loginRequest = new GoogleLoginRequest(
                account.getName(),
                account.getEmail(),
                account.getIdRole(),
                account.getMessage()
        );

        RetrofitInstance.getRetrofitInstance().apiInterface.loginwithgoogle(loginRequest).enqueue(new Callback<GoogleLoginRequest>() {
            @Override
            public void onResponse(Call<GoogleLoginRequest> call, Response<GoogleLoginRequest> response) {
                if (response.isSuccessful()) {
                    GoogleLoginRequest apiResponse = response.body();
                    if (apiResponse != null) {
                        int idRole = apiResponse.getIdRole();
                        String message = apiResponse.getMessage();

                        if ("Email đăng nhập không nằm trong danh sách CBVC của trường, vui lòng kiểm tra lại".equals(message)) {
                            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Oops...")
                                    .setContentText(message)
                                    .setConfirmClickListener(sweetAlertDialog -> {
                                        sweetAlertDialog.dismissWithAnimation();
                                        LogoutAndRetry(); // Đăng xuất và cho phép chọn tài khoản khác
                                    })
                                    .show();
                            return;
                        }

                        if (idRole == 1) {
                            GotoUser();
                        } else if (idRole == 2) {
                            GoToAdmin();
                        } else if (idRole == 3) {
                            GotoKiemDuyet();
                        }
                    }
                } else {
                    new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Something went wrong!")
                            .show();
                }
            }

            @Override
            public void onFailure(Call<GoogleLoginRequest> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void LogoutAndRetry() {
        gsc.signOut().addOnCompleteListener(this, task -> {
            Intent signInIntent = gsc.getSignInIntent();
            startActivityForResult(signInIntent, 1000);
        });
    }

    private void GoToAdmin(){
        finish();
        Intent intent = new Intent(MainActivity.this, MainAdminActivity.class);
        startActivity(intent);
    }

    public void GotoUser(){
        finish();
        Intent intent = new Intent(MainActivity.this, DanhSachThietBiMuonActivity.class);
        startActivity(intent);
    }

    public void GotoKiemDuyet(){
        finish();
        Intent intent = new Intent(MainActivity.this, DanhSachThietBiMuonAllUserActivity.class);
        startActivity(intent);
    }
}