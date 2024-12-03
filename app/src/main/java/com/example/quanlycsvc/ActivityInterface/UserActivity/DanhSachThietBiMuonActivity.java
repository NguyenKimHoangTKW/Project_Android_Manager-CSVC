package com.example.quanlycsvc.ActivityInterface.UserActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quanlycsvc.ActivityInterface.MainActivity;
import com.example.quanlycsvc.ActivityInterface.MainAdminActivity;
import com.example.quanlycsvc.AdapterModels.DanhSachMuonAdapter;
import com.example.quanlycsvc.Api.RetrofitInstance;
import com.example.quanlycsvc.Models.DanhSachThietBiUser;
import com.example.quanlycsvc.Models.GetInfoCBVC;
import com.example.quanlycsvc.Models.PhongHoc;
import com.example.quanlycsvc.Models.UserMuonThietBi;
import com.example.quanlycsvc.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachThietBiMuonActivity extends AppCompatActivity {
    ListView lvdanhsachmuonthietbi;
    ArrayList<DanhSachThietBiUser> list;
    DanhSachMuonAdapter adapter;
    Spinner spinnerLoai;
    ArrayList<String> loaiThietBiList;
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;
    TextView tvShowNameUser,tvShowEmail;
    Button btnViewThietBiDkMuon,btnDangXuat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_danh_sach_thiet_bi_muon);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Set_Control();
        Show_All_ThietBi();
    }
    public void Set_Control(){
        lvdanhsachmuonthietbi = findViewById(R.id.lvdanhsachmuonthietbi);
        spinnerLoai = findViewById(R.id.spinnerLoai);
        btnDangXuat = findViewById(R.id.btnDangXuat);
        btnViewThietBiDkMuon = findViewById(R.id.btnViewThietBiDkMuon);
        list = new ArrayList<>();
        adapter = new DanhSachMuonAdapter(DanhSachThietBiMuonActivity.this,list);
        loaiThietBiList = new ArrayList<>();
        lvdanhsachmuonthietbi.setAdapter(adapter);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        get_info_user(acct);
        lvdanhsachmuonthietbi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DanhSachThietBiUser danhSachThietBiUser = list.get(position);
                showDialog(danhSachThietBiUser,acct);
            }
        });

        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });
        btnViewThietBiDkMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_thiet_bi_da_muon();
            }
        });
    }

    public void show_thiet_bi_da_muon(){
        Intent intent = new Intent(DanhSachThietBiMuonActivity.this,ListThietBiDaMuonActivity.class);
        startActivity(intent);
    }
    public void get_info_user(GoogleSignInAccount acct){
        tvShowNameUser = findViewById(R.id.tvShowNameUser);
        tvShowEmail = findViewById(R.id.tvShowEmail);
        GetInfoCBVC getInfoCBVC = new GetInfoCBVC(null,acct.getEmail());
        RetrofitInstance.getRetrofitInstance().apiInterface.getinfocbvc(getInfoCBVC).enqueue(new Callback<GetInfoCBVC>() {
            @Override
            public void onResponse(Call<GetInfoCBVC> call, Response<GetInfoCBVC> response) {
                GetInfoCBVC apiResponse = response.body();
                tvShowNameUser.setText(apiResponse.getTen_cbvc());
                tvShowEmail.setText(acct.getEmail());
            }

            @Override
            public void onFailure(Call<GetInfoCBVC> call, Throwable t) {

            }
        });
    }
    public void Show_All_ThietBi(){
        RetrofitInstance.getRetrofitInstance().apiInterface.getfullthietbi().enqueue(new Callback<List<DanhSachThietBiUser>>() {
            @Override
            public void onResponse(Call<List<DanhSachThietBiUser>> call, Response<List<DanhSachThietBiUser>> response) {
                if(response.isSuccessful()){
                    list.clear();
                    list.addAll(response.body());
                    adapter.notifyDataSetChanged();
                    SetUpSpinner();
                }
            }
            @Override
            public void onFailure(Call<List<DanhSachThietBiUser>> call, Throwable t) {

            }
        });
    }
    public void FilterByLoai(String loai) {
        ArrayList<DanhSachThietBiUser> filteredList = new ArrayList<>();
        if (loai.equals("Tất cả")) {
            filteredList.addAll(list);
        } else {
            for (DanhSachThietBiUser item : list) {
                if (item.getTen_phan_loai().equals(loai)) {
                    filteredList.add(item);
                }
            }
        }
        adapter = new DanhSachMuonAdapter(DanhSachThietBiMuonActivity.this, filteredList);
        lvdanhsachmuonthietbi.setAdapter(adapter);
    }
    public void SetUpSpinner() {
        loaiThietBiList.clear();
        loaiThietBiList.add("Tất cả");
        for (DanhSachThietBiUser item : list) {
            if (!loaiThietBiList.contains(item.getTen_phan_loai())) {
                loaiThietBiList.add(item.getTen_phan_loai());
            }
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, loaiThietBiList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLoai.setAdapter(spinnerAdapter);

        spinnerLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLoai = loaiThietBiList.get(position);
                FilterByLoai(selectedLoai);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void showDialog(DanhSachThietBiUser danhSachThietBiUser, GoogleSignInAccount acct) {
        Spinner spinPhongHoc;
        EditText edtSoluongmuon, edtYeuCau;
        TextView edtTenPhongHocU;
        Button btnDangkymuon, btnHuydangkymuon;

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_user_muon_thiet_bi, null);

        spinPhongHoc = dialogView.findViewById(R.id.spinPhongHoc);
        edtSoluongmuon = dialogView.findViewById(R.id.edtSoluongmuon);
        edtYeuCau = dialogView.findViewById(R.id.edtYeuCau);
        edtTenPhongHocU = dialogView.findViewById(R.id.edtTenPhongHocU);
        btnHuydangkymuon = dialogView.findViewById(R.id.btnHuydangkymuon);
        btnDangkymuon = dialogView.findViewById(R.id.btnDangkymuon);

        edtTenPhongHocU.setText(danhSachThietBiUser.ten_thiet_bi);
        edtTenPhongHocU.setEnabled(false);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        RetrofitInstance.getRetrofitInstance().apiInterface.getfullphonghoc().enqueue(new Callback<List<PhongHoc>>() {
            @Override
            public void onResponse(Call<List<PhongHoc>> call, Response<List<PhongHoc>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<PhongHoc> phongHocList = response.body();
                    List<String> tenPhongHocList = new ArrayList<>();
                    for (PhongHoc phongHoc : phongHocList) {
                        tenPhongHocList.add(phongHoc.getTen_phong_hoc());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            DanhSachThietBiMuonActivity.this,
                            android.R.layout.simple_spinner_item,
                            tenPhongHocList
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinPhongHoc.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<PhongHoc>> call, Throwable t) {
                Toast.makeText(DanhSachThietBiMuonActivity.this, "Không lấy được danh sách phòng học", Toast.LENGTH_SHORT).show();
            }
        });

        btnDangkymuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String personEmail = acct.getEmail();
                int soLuongMuon;

                try {
                    soLuongMuon = Integer.parseInt(edtSoluongmuon.getText().toString().trim());
                } catch (NumberFormatException e) {
                    Toast.makeText(DanhSachThietBiMuonActivity.this, "Vui lòng nhập số lượng cần mượn", Toast.LENGTH_SHORT).show();
                    return;
                }

                String yeuCau = edtYeuCau.getText().toString().trim();
                String selectedPhongHoc = spinPhongHoc.getSelectedItem().toString();

                UserMuonThietBi userMuonThietBi = new UserMuonThietBi(
                        personEmail,
                        selectedPhongHoc,
                        danhSachThietBiUser.ten_thiet_bi,
                        soLuongMuon,
                        yeuCau,
                        null
                );
                RetrofitInstance.getRetrofitInstance().apiInterface.user_muon_thiet_bi(userMuonThietBi).enqueue(new Callback<UserMuonThietBi>() {
                    @Override
                    public void onResponse(Call<UserMuonThietBi> call, Response<UserMuonThietBi> response) {
                        UserMuonThietBi apiResponse = response.body();
                        if(response.isSuccessful()){
                            Toast.makeText(DanhSachThietBiMuonActivity.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(DanhSachThietBiMuonActivity.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserMuonThietBi> call, Throwable t) {
                    }
                });
            }
        });

        btnHuydangkymuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void Logout() {
        gsc.signOut().addOnCompleteListener(this, task -> {
            startActivity(new Intent(DanhSachThietBiMuonActivity.this, MainActivity.class));
            finish();
        });
    }

}