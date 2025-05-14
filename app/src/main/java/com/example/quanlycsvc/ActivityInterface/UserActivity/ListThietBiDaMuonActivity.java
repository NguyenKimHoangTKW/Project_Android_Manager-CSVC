package com.example.quanlycsvc.ActivityInterface.UserActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quanlycsvc.AdapterModels.DanhSachDaMuonAdapter;
import com.example.quanlycsvc.Api.RetrofitInstance;
import com.example.quanlycsvc.Models.DanhSachThietBiDaMuonUser;
import com.example.quanlycsvc.Models.GetInfoCBVC;
import com.example.quanlycsvc.Models.HuyMuonUser;
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

public class ListThietBiDaMuonActivity extends AppCompatActivity {
    ListView listview;
    ArrayList<DanhSachThietBiDaMuonUser> list;
    DanhSachDaMuonAdapter adapter;
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;
    Button btnBacktodanhsachmuon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_thiet_bi_da_muon);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Set_Control();
    }

    public void Set_Control() {
        listview = findViewById(R.id.lvDanhSachThietBiDaMuons);
        list = new ArrayList<>();
        adapter = new DanhSachDaMuonAdapter(ListThietBiDaMuonActivity.this, list);
        listview.setAdapter(adapter);
        btnBacktodanhsachmuon = findViewById(R.id.btnBacktodanhsachmuon);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        Get_Full(acct);
        btnBacktodanhsachmuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DanhSachThietBiDaMuonUser danhSachThietBiDaMuonUser = list.get(i);
                Log.e("EEEE","SADSAD");
                if ("Đã hủy".equals(danhSachThietBiDaMuonUser.ten_trang_thaii)) {
                    Toast.makeText(ListThietBiDaMuonActivity.this, "Bạn đã hủy mượn thiết bị này", Toast.LENGTH_SHORT).show();
                }
                else if("Đã trả".equals(danhSachThietBiDaMuonUser.ten_trang_thaii)){
                    Toast.makeText(ListThietBiDaMuonActivity.this, "Bạn đã trả thiết bị này", Toast.LENGTH_SHORT).show();
                }
                else {
                    ShowDialog(danhSachThietBiDaMuonUser);
                }
            }
        });


    }

    public void Get_Full(GoogleSignInAccount acct) {
        GetInfoCBVC getInfoCBVC = new GetInfoCBVC(null, acct.getEmail());
        RetrofitInstance.getRetrofitInstance()
                .apiInterface
                .get_full_thiet_bi_user_muon(getInfoCBVC)
                .enqueue(new Callback<List<DanhSachThietBiDaMuonUser>>() {
                    @Override
                    public void onResponse(Call<List<DanhSachThietBiDaMuonUser>> call, Response<List<DanhSachThietBiDaMuonUser>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            list.clear();
                            list.addAll(response.body());
                            adapter.notifyDataSetChanged(); // Cập nhật giao diện
                            Log.d("SUCCESS", "Data loaded: " + list.size());
                        } else {
                            Log.e("ERROR", "Response unsuccessful or body is null");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<DanhSachThietBiDaMuonUser>> call, Throwable t) {
                        Log.e("ERROR", "API call failed: " + t.getMessage());
                    }
                });
    }

    public void ShowDialog(DanhSachThietBiDaMuonUser danhSachThietBiDaMuonUser){
        TextView tvTenThietBiHuy,tvPhonghocdangkymuonthietbihuy;
        EditText edtLydohuymuon;
        Button btnXacnhanhuy,btnThoatXacnhanhuy;
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_huy_muon_thiet_bi_user, null);

        tvTenThietBiHuy = dialogView.findViewById(R.id.tvTenThietBiHuy);
        tvPhonghocdangkymuonthietbihuy = dialogView.findViewById(R.id.tvPhonghocdangkymuonthietbihuy);
        edtLydohuymuon = dialogView.findViewById(R.id.edtLydohuymuon);
        btnXacnhanhuy = dialogView.findViewById(R.id.btnXacnhanhuy);
        btnThoatXacnhanhuy = dialogView.findViewById(R.id.btnThoatXacnhanhuy);

        tvTenThietBiHuy.setText("Tên thiết bị muốn hủy mượn: " + danhSachThietBiDaMuonUser.ten_thiet_bi);
        tvPhonghocdangkymuonthietbihuy.setText("Phòng học đăng ký mượn: "+ danhSachThietBiDaMuonUser.ten_phong_hoc);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        btnXacnhanhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Lydohuymuon = edtLydohuymuon.getText().toString().trim();
                if(Lydohuymuon.isEmpty()){
                    Toast.makeText(ListThietBiDaMuonActivity.this, "Vui lòng nhập lý do bạn hủy (Bắt buộc)", Toast.LENGTH_SHORT).show();
                    return;
                }
                HuyMuonUser huyMuonUser = new HuyMuonUser(danhSachThietBiDaMuonUser.id_danh_sach_muon,Lydohuymuon,null);
                RetrofitInstance.getRetrofitInstance().apiInterface.huymuonuser(huyMuonUser).enqueue(new Callback<HuyMuonUser>() {
                    @Override
                    public void onResponse(Call<HuyMuonUser> call, Response<HuyMuonUser> response) {
                        HuyMuonUser apiResponse = response.body();
                        if(response.isSuccessful()){
                            Toast.makeText(ListThietBiDaMuonActivity.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            Get_Full(acct);
                        }
                    }

                    @Override
                    public void onFailure(Call<HuyMuonUser> call, Throwable t) {

                    }
                });
            }
        });
        btnThoatXacnhanhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dialog.dismiss();
            }
        });
        dialog.show();
    }
}
