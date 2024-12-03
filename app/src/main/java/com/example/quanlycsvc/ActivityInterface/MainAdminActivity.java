package com.example.quanlycsvc.ActivityInterface;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.quanlycsvc.Api.RetrofitInstance;
import com.example.quanlycsvc.Models.GetInfoCBVC;
import com.example.quanlycsvc.R;
import com.example.quanlycsvc.databinding.ActivityMainAdminBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainAdminActivity extends AppCompatActivity {
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;
    private TextView showNameLogin, showEmailLogin;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainAdminBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMainAdmin.toolbar);
        binding.appBarMainAdmin.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.fab).show();
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_account,R.id.nav_phonghoc)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_admin);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Cấu hình Google Sign-In
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        get_thong_tin(navigationView, acct);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                DrawerLayout drawerLayout = binding.drawerLayout;
                if (item.getItemId() == R.id.nav_logout) {
                    Logout();
                } else {
                    NavController navController = Navigation.findNavController(MainAdminActivity.this, R.id.nav_host_fragment_content_main_admin);
                    NavigationUI.onNavDestinationSelected(item, navController);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    private void Logout() {
        gsc.signOut().addOnCompleteListener(this, task -> {
            startActivity(new Intent(MainAdminActivity.this, MainActivity.class));
            finish();
        });
    }

    public void get_thong_tin(NavigationView navigationView, GoogleSignInAccount acct) {
        View headerView = navigationView.getHeaderView(0);
        showNameLogin = headerView.findViewById(R.id.showNameLogin);
        showEmailLogin = headerView.findViewById(R.id.showEmailLogin);

        if (acct != null) {
            String personEmail = acct.getEmail();
            GetInfoCBVC getInfoCBVC = new GetInfoCBVC(null, personEmail);
            RetrofitInstance.getRetrofitInstance().apiInterface.getinfocbvc(getInfoCBVC).enqueue(new Callback<GetInfoCBVC>() {
                @Override
                public void onResponse(Call<GetInfoCBVC> call, Response<GetInfoCBVC> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        GetInfoCBVC getInfo = response.body();
                        showNameLogin.setText(getInfo.getTen_cbvc());
                        showEmailLogin.setText(getInfo.getEmail());
                    } else {
                        Log.e("API Response", "No data or error code: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<GetInfoCBVC> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_admin, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_admin);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
