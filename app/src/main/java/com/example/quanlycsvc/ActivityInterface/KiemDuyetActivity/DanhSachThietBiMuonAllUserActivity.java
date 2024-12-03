package com.example.quanlycsvc.ActivityInterface.KiemDuyetActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.quanlycsvc.ActivityInterface.UserActivity.DanhSachThietBiMuonActivity;
import com.example.quanlycsvc.AdapterModels.DanhSachFullThietBiMuonAdapter;
import com.example.quanlycsvc.Api.RetrofitInstance;
import com.example.quanlycsvc.Models.DroplistTrangThai;
import com.example.quanlycsvc.Models.DuyetMuonChoNguoiDung;
import com.example.quanlycsvc.Models.GetFullThietBiMuon;
import com.example.quanlycsvc.Models.GetInfoCBVC;
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

public class DanhSachThietBiMuonAllUserActivity extends AppCompatActivity {
    ListView lvFullThietBiMuon;
    ArrayList<GetFullThietBiMuon> list;
    DanhSachFullThietBiMuonAdapter adapter;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView tvNameUser,tvGetEmail;
    Button btnLogoutt;
    Spinner spinnerTrangThaiFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_danh_sach_thiet_bi_muon_all_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Set_Control();
        Show_All_ThietBi();
    }

    public void Set_Control(){
        lvFullThietBiMuon = findViewById(R.id.lvFullThietBiMuon);
        list = new ArrayList<>();
        spinnerTrangThaiFilter = findViewById(R.id.spinnerTrangThaiFilter);
        adapter = new DanhSachFullThietBiMuonAdapter(DanhSachThietBiMuonAllUserActivity.this,list);
        lvFullThietBiMuon.setAdapter(adapter);
        btnLogoutt = findViewById(R.id.btnLogoutt);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        get_info_user(acct);
        lvFullThietBiMuon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GetFullThietBiMuon getFullThietBiMuon = list.get(position);
                if("Đã trả".equals(getFullThietBiMuon.ten_trang_thaii)){
                    Toast.makeText(DanhSachThietBiMuonAllUserActivity.this, "Thiết bị mượn này đã trả", Toast.LENGTH_SHORT).show();
                }
                else if("Đã hủy".equals(getFullThietBiMuon.ten_trang_thaii)){
                    Toast.makeText(DanhSachThietBiMuonAllUserActivity.this, "Thiết bị mượn này đã huỷ", Toast.LENGTH_SHORT).show();
                }
                else{
                    showDialog(getFullThietBiMuon);
                }
            }
        });

        btnLogoutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });
        loadTrangThaiFilter();
        spinnerTrangThaiFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedTrangThai = parent.getItemAtPosition(position).toString();
                filterThietBiMuon(selectedTrangThai);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                filterThietBiMuon("Tất cả");
            }
        });
    }
    public void loadTrangThaiFilter() {
        RetrofitInstance.getRetrofitInstance().apiInterface.droplisttrangthai().enqueue(new Callback<List<DroplistTrangThai>>() {
            @Override
            public void onResponse(Call<List<DroplistTrangThai>> call, Response<List<DroplistTrangThai>> response) {
                if (response.isSuccessful()) {
                    List<DroplistTrangThai> droplistTrangThaiList = response.body();
                    List<String> tenTrangThai = new ArrayList<>();
                    tenTrangThai.add("Tất cả");
                    for (DroplistTrangThai trangThai : droplistTrangThaiList) {
                        tenTrangThai.add(trangThai.getTen_trang_thaii());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            DanhSachThietBiMuonAllUserActivity.this,
                            android.R.layout.simple_spinner_item,
                            tenTrangThai
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerTrangThaiFilter.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<DroplistTrangThai>> call, Throwable t) {
                Toast.makeText(DanhSachThietBiMuonAllUserActivity.this, "Không thể tải trạng thái lọc", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void filterThietBiMuon(String trangThai) {
        if ("Tất cả".equals(trangThai)) {
            Show_All_ThietBi();
        } else {
            ArrayList<GetFullThietBiMuon> filteredList = new ArrayList<>();
            for (GetFullThietBiMuon item : list) {
                if (trangThai.equals(item.ten_trang_thaii)) {
                    filteredList.add(item);
                }
            }
            adapter = new DanhSachFullThietBiMuonAdapter(DanhSachThietBiMuonAllUserActivity.this, filteredList);
            lvFullThietBiMuon.setAdapter(adapter);
        }
    }

    public void Show_All_ThietBi(){
        RetrofitInstance.getRetrofitInstance().apiInterface.getfullthietbimuon().enqueue(new Callback<List<GetFullThietBiMuon>>() {
            @Override
            public void onResponse(Call<List<GetFullThietBiMuon>> call, Response<List<GetFullThietBiMuon>> response) {
                if(response.isSuccessful()){
                    list.clear();
                    list.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<GetFullThietBiMuon>> call, Throwable t) {

            }
        });
    }
    public void get_info_user(GoogleSignInAccount acct){
        tvNameUser = findViewById(R.id.tvNameUser);
        tvGetEmail = findViewById(R.id.tvGetEmail);
        GetInfoCBVC getInfoCBVC = new GetInfoCBVC(null,acct.getEmail());
        RetrofitInstance.getRetrofitInstance().apiInterface.getinfocbvc(getInfoCBVC).enqueue(new Callback<GetInfoCBVC>() {
            @Override
            public void onResponse(Call<GetInfoCBVC> call, Response<GetInfoCBVC> response) {
                GetInfoCBVC apiResponse = response.body();
                tvNameUser.setText(apiResponse.getTen_cbvc());
                tvGetEmail.setText(acct.getEmail());
            }

            @Override
            public void onFailure(Call<GetInfoCBVC> call, Throwable t) {

            }
        });
    }
    public void showDialog(GetFullThietBiMuon getFullThietBiMuon){
        Spinner spinDropTrangThai;
        TextView tvduyettencbvc,tvduyettenthietbi,tvrowphonghocduyemuon;
        EditText edtThongBaoDenUser;
        Button btnduyetmuon,btnthoatduyetmuon;
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.duyet_muon_row, null);
        spinDropTrangThai = dialogView.findViewById(R.id.spinDropTrangThai);
        tvduyettencbvc = dialogView.findViewById(R.id.tvduyettencbvc);
        tvduyettenthietbi = dialogView.findViewById(R.id.tvduyettenthietbi);
        tvrowphonghocduyemuon = dialogView.findViewById(R.id.tvrowphonghocduyemuon);

        edtThongBaoDenUser = dialogView.findViewById(R.id.edtThongBaoDenUser);
        btnduyetmuon = dialogView.findViewById(R.id.btnduyetmuon);
        btnthoatduyetmuon = dialogView.findViewById(R.id.btnthoatduyetmuon);

        tvduyettencbvc.setText("Tên CBVC/GV mượn: "+ getFullThietBiMuon.name_CBVC);
        tvduyettenthietbi.setText("Tên thiết bị mượn: "+ getFullThietBiMuon.ten_thiet_bi);
        tvrowphonghocduyemuon.setText("Phòng học mượn: "+ getFullThietBiMuon.ten_phong_hoc);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        RetrofitInstance.getRetrofitInstance().apiInterface.droplisttrangthai().enqueue(new Callback<List<DroplistTrangThai>>() {
            @Override
            public void onResponse(Call<List<DroplistTrangThai>> call, Response<List<DroplistTrangThai>> response) {
                List<DroplistTrangThai> droplistTrangThaiList = response.body();
                List<String> tenTrangThai = new ArrayList<>();
                for(DroplistTrangThai trangThai : droplistTrangThaiList){
                    tenTrangThai.add(trangThai.getTen_trang_thaii());
                }
                ArrayAdapter<String> adapter1 = new ArrayAdapter<>(
                        DanhSachThietBiMuonAllUserActivity.this,
                        android.R.layout.simple_spinner_item,
                        tenTrangThai
                );
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinDropTrangThai.setAdapter(adapter1);
            }

            @Override
            public void onFailure(Call<List<DroplistTrangThai>> call, Throwable t) {
                Toast.makeText(DanhSachThietBiMuonAllUserActivity.this, "Không lấy được danh sách trạng thái", Toast.LENGTH_SHORT).show();
            }
        });

        btnduyetmuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedTrangThai = spinDropTrangThai.getSelectedItem().toString();
                String thongbao = edtThongBaoDenUser.getText().toString().trim();
                DuyetMuonChoNguoiDung duyetMuonChoNguoiDung = new DuyetMuonChoNguoiDung(
                        getFullThietBiMuon.id_danh_sach_muon,
                        getFullThietBiMuon.ten_thiet_bi,
                        selectedTrangThai,
                        thongbao,
                        null
                );
                RetrofitInstance.getRetrofitInstance().apiInterface.duyetmuonchonguoidung(duyetMuonChoNguoiDung).enqueue(new Callback<DuyetMuonChoNguoiDung>() {
                    @Override
                    public void onResponse(Call<DuyetMuonChoNguoiDung> call, Response<DuyetMuonChoNguoiDung> response) {
                        DuyetMuonChoNguoiDung apiResponse = response.body();
                        if(response.isSuccessful()){
                            Toast.makeText(DanhSachThietBiMuonAllUserActivity.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            Show_All_ThietBi();
                        }
                        else{
                            Toast.makeText(DanhSachThietBiMuonAllUserActivity.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DuyetMuonChoNguoiDung> call, Throwable t) {

                    }
                });
            }
        });

        btnthoatduyetmuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void Logout() {
        gsc.signOut().addOnCompleteListener(this, task -> {
            startActivity(new Intent(DanhSachThietBiMuonAllUserActivity.this, MainActivity.class));
            finish();
        });
    }
}