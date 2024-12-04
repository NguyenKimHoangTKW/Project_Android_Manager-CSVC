package com.example.quanlycsvc.ActivityInterface.ui.thietbi;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlycsvc.ActivityInterface.UserActivity.DanhSachThietBiMuonActivity;
import com.example.quanlycsvc.ActivityInterface.ui.home.HomeViewModel;
import com.example.quanlycsvc.AdapterModels.DanhSachFullThietBiAdapter;
import com.example.quanlycsvc.AdapterModels.PhongHocAdapter;
import com.example.quanlycsvc.Api.RetrofitInstance;
import com.example.quanlycsvc.Models.DanhSachThietBiUser;
import com.example.quanlycsvc.Models.DonViTinh;
import com.example.quanlycsvc.Models.PhanLoai;
import com.example.quanlycsvc.Models.PhongHoc;
import com.example.quanlycsvc.Models.ThuongHieu;
import com.example.quanlycsvc.Models.UpdateThietBi;
import com.example.quanlycsvc.R;
import com.example.quanlycsvc.databinding.FragmentHomeBinding;
import com.example.quanlycsvc.databinding.FragmentPhongHocBinding;
import com.example.quanlycsvc.databinding.FragmentThietBiBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThietBiFragment extends Fragment {
    ListView lvThietBiAdmin;
    ArrayList<DanhSachThietBiUser> list;
    DanhSachFullThietBiAdapter adapter;
    Button btnThemMoiThietbi;
    private FragmentThietBiBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentThietBiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Set_Control();
        return root;
    }
    public void Set_Control(){
        lvThietBiAdmin = binding.lvThietBiAdmin;
        list = new ArrayList<>();
        adapter = new DanhSachFullThietBiAdapter(requireContext(),list);
        lvThietBiAdmin.setAdapter(adapter);
        btnThemMoiThietbi = binding.btnThemMoiThietbi;
        Show_All_Data();
        lvThietBiAdmin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DanhSachThietBiUser danhSachThietBiUser = list.get(position);
                showDiaLogEdit(danhSachThietBiUser);
            }
        });

        btnThemMoiThietbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiaLogAddd();
            }
        });
    }

    public void Show_All_Data(){
        RetrofitInstance.getRetrofitInstance().apiInterface.getfullthietbi().enqueue(new Callback<List<DanhSachThietBiUser>>() {
            @Override
            public void onResponse(Call<List<DanhSachThietBiUser>> call, Response<List<DanhSachThietBiUser>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    list.clear();
                    list.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("API Response", "Không thành công, mã lỗi: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<DanhSachThietBiUser>> call, Throwable t) {
                Log.e("API Error", "Không thể lấy dữ liệu");

            }
        });
    }

    public void showDiaLogAddd() {
        View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.them_moi_thiet_bi_row, null);
        AlertDialog dialog = new AlertDialog.Builder(requireContext()).setView(dialogView).create();

        EditText edtAddTenthietbi = dialogView.findViewById(R.id.edtAddTenthietbi);
        EditText edtAddThongSo = dialogView.findViewById(R.id.edtAddThongSo);
        Spinner spinChonThuongHieu = dialogView.findViewById(R.id.spinChonThuongHieu);
        EditText edtNhapSoLuong = dialogView.findViewById(R.id.edtNhapSoLuong);
        EditText edtNhapMoTa = dialogView.findViewById(R.id.edtNhapMoTa);
        Spinner spinChonDonViTinh = dialogView.findViewById(R.id.spinChonDonViTinh);
        Spinner spinPhanLoai = dialogView.findViewById(R.id.spinPhanLoai);
        Button btnThemmoithietbi = dialogView.findViewById(R.id.btnThemmoithietbi);
        Button btnThoatThietBi = dialogView.findViewById(R.id.btnThoatThietBi);

        RetrofitInstance.getRetrofitInstance().apiInterface.droplistThuongHieu().enqueue(new Callback<List<ThuongHieu>>() {
            @Override
            public void onResponse(Call<List<ThuongHieu>> call, Response<List<ThuongHieu>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ThuongHieu> thuongHieuList = response.body();
                    List<String> thuongHieuNames = new ArrayList<>();
                    for (ThuongHieu item : thuongHieuList) {
                        thuongHieuNames.add(item.ten_thuong_hieu);
                    }
                    ArrayAdapter<String> adapterThuongHieu = new ArrayAdapter<>(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            thuongHieuNames
                    );
                    adapterThuongHieu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinChonThuongHieu.setAdapter(adapterThuongHieu);
                }
            }

            @Override
            public void onFailure(Call<List<ThuongHieu>> call, Throwable t) {
                Toast.makeText(requireContext(), "Failed to load data for Thuong Hieu", Toast.LENGTH_SHORT).show();
            }
        });

        RetrofitInstance.getRetrofitInstance().apiInterface.droplistDonViTinh().enqueue(new Callback<List<DonViTinh>>() {
            @Override
            public void onResponse(Call<List<DonViTinh>> call, Response<List<DonViTinh>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<DonViTinh> donViTinhList = response.body();
                    List<String> donViTinhNames = new ArrayList<>();
                    for (DonViTinh item : donViTinhList) {
                        donViTinhNames.add(item.ten_don_vi_tinh);
                    }
                    ArrayAdapter<String> adapterDonViTinh = new ArrayAdapter<>(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            donViTinhNames
                    );
                    adapterDonViTinh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinChonDonViTinh.setAdapter(adapterDonViTinh);
                }
            }

            @Override
            public void onFailure(Call<List<DonViTinh>> call, Throwable t) {
                Toast.makeText(requireContext(), "Failed to load data for Don Vi Tinh", Toast.LENGTH_SHORT).show();
            }
        });

        RetrofitInstance.getRetrofitInstance().apiInterface.droplistPhanLoai().enqueue(new Callback<List<PhanLoai>>() {
            @Override
            public void onResponse(Call<List<PhanLoai>> call, Response<List<PhanLoai>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<PhanLoai> phanLoaiList = response.body();
                    List<String> phanLoaiNames = new ArrayList<>();
                    for (PhanLoai item : phanLoaiList) {
                        phanLoaiNames.add(item.ten_phan_loai);
                    }
                    ArrayAdapter<String> adapterPhanLoai = new ArrayAdapter<>(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            phanLoaiNames
                    );
                    adapterPhanLoai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinPhanLoai.setAdapter(adapterPhanLoai);
                }
            }

            @Override
            public void onFailure(Call<List<PhanLoai>> call, Throwable t) {
                Toast.makeText(requireContext(), "Failed to load data for Phan Loai", Toast.LENGTH_SHORT).show();
            }
        });

        btnThemmoithietbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenthuonghieu = spinChonThuongHieu.getSelectedItem().toString();
                String tendonvi = spinChonDonViTinh.getSelectedItem().toString();
                String tenphanloai = spinPhanLoai.getSelectedItem().toString();
                int soLuong;
                try {
                    soLuong = Integer.parseInt(edtNhapSoLuong.getText().toString().trim());
                } catch (NumberFormatException e) {
                    Toast.makeText(requireContext(), "Vui lòng nhập số lượng (Bắt buọc): ", Toast.LENGTH_SHORT).show();
                    return;
                }

                UpdateThietBi updateThietBi = new UpdateThietBi(
                        0,
                        edtAddTenthietbi.getText().toString().trim(),
                        edtAddThongSo.getText().toString().trim(),
                        tenthuonghieu,
                        soLuong,
                        edtNhapMoTa.getText().toString().trim(),
                        tendonvi,
                        tenphanloai,
                        null
                );

                RetrofitInstance.getRetrofitInstance().apiInterface.them_moi_thiet_bi(updateThietBi).enqueue(new Callback<UpdateThietBi>() {
                    @Override
                    public void onResponse(Call<UpdateThietBi> call, Response<UpdateThietBi> response) {
                        UpdateThietBi apiResponse = response.body();
                        if(response.isSuccessful()){
                            Toast.makeText(requireContext(), apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            Show_All_Data();
                        }
                        else{
                            Toast.makeText(requireContext(), apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateThietBi> call, Throwable t) {

                    }
                });
            }
        });

        btnThoatThietBi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public void showDiaLogEdit(DanhSachThietBiUser danhSachThietBiUser) {
        View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.them_moi_thiet_bi_row, null);
        AlertDialog dialog = new AlertDialog.Builder(requireContext()).setView(dialogView).create();

        EditText edtAddTenthietbi = dialogView.findViewById(R.id.edtAddTenthietbi);
        EditText edtAddThongSo = dialogView.findViewById(R.id.edtAddThongSo);
        Spinner spinChonThuongHieu = dialogView.findViewById(R.id.spinChonThuongHieu);
        EditText edtNhapSoLuong = dialogView.findViewById(R.id.edtNhapSoLuong);
        EditText edtNhapMoTa = dialogView.findViewById(R.id.edtNhapMoTa);
        Spinner spinChonDonViTinh = dialogView.findViewById(R.id.spinChonDonViTinh);
        Spinner spinPhanLoai = dialogView.findViewById(R.id.spinPhanLoai);
        Button btnThemmoithietbi = dialogView.findViewById(R.id.btnThemmoithietbi);
        Button btnThoatThietBi = dialogView.findViewById(R.id.btnThoatThietBi);

        edtAddTenthietbi.setText(danhSachThietBiUser.ten_thiet_bi);
        edtAddThongSo.setText(danhSachThietBiUser.thong_so);
        edtNhapSoLuong.setText(String.valueOf(danhSachThietBiUser.so_luong));
        edtNhapMoTa.setText(danhSachThietBiUser.mo_ta);

        RetrofitInstance.getRetrofitInstance().apiInterface.droplistThuongHieu().enqueue(new Callback<List<ThuongHieu>>() {
            @Override
            public void onResponse(Call<List<ThuongHieu>> call, Response<List<ThuongHieu>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ThuongHieu> thuongHieuList = response.body();
                    List<String> thuongHieuNames = new ArrayList<>();
                    int selectedPosition = 0;
                    for (int i = 0; i < thuongHieuList.size(); i++) {
                        thuongHieuNames.add(thuongHieuList.get(i).ten_thuong_hieu);
                        if (thuongHieuList.get(i).ten_thuong_hieu.equals(danhSachThietBiUser.ten_thuong_hieu)) {
                            selectedPosition = i;
                        }
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            thuongHieuNames
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinChonThuongHieu.setAdapter(adapter);
                    spinChonThuongHieu.setSelection(selectedPosition);
                }
            }

            @Override
            public void onFailure(Call<List<ThuongHieu>> call, Throwable t) {
                Toast.makeText(requireContext(), "Failed to load data for Thuong Hieu", Toast.LENGTH_SHORT).show();
            }
        });

        RetrofitInstance.getRetrofitInstance().apiInterface.droplistDonViTinh().enqueue(new Callback<List<DonViTinh>>() {
            @Override
            public void onResponse(Call<List<DonViTinh>> call, Response<List<DonViTinh>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<DonViTinh> donViTinhList = response.body();
                    List<String> donViTinhNames = new ArrayList<>();
                    int selectedPosition = 0;
                    for (int i = 0; i < donViTinhList.size(); i++) {
                        donViTinhNames.add(donViTinhList.get(i).ten_don_vi_tinh);
                        if (donViTinhList.get(i).ten_don_vi_tinh.equals(danhSachThietBiUser.ten_don_vi_tinh)) {
                            selectedPosition = i;
                        }
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            donViTinhNames
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinChonDonViTinh.setAdapter(adapter);
                    spinChonDonViTinh.setSelection(selectedPosition);
                }
            }

            @Override
            public void onFailure(Call<List<DonViTinh>> call, Throwable t) {
                Toast.makeText(requireContext(), "Failed to load data for Don Vi Tinh", Toast.LENGTH_SHORT).show();
            }
        });

        RetrofitInstance.getRetrofitInstance().apiInterface.droplistPhanLoai().enqueue(new Callback<List<PhanLoai>>() {
            @Override
            public void onResponse(Call<List<PhanLoai>> call, Response<List<PhanLoai>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<PhanLoai> phanLoaiList = response.body();
                    List<String> phanLoaiNames = new ArrayList<>();
                    int selectedPosition = 0;
                    for (int i = 0; i < phanLoaiList.size(); i++) {
                        phanLoaiNames.add(phanLoaiList.get(i).ten_phan_loai);
                        if (phanLoaiList.get(i).ten_phan_loai.equals(danhSachThietBiUser.ten_phan_loai)) {
                            selectedPosition = i;
                        }
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            phanLoaiNames
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinPhanLoai.setAdapter(adapter);
                    spinPhanLoai.setSelection(selectedPosition);
                }
            }

            @Override
            public void onFailure(Call<List<PhanLoai>> call, Throwable t) {
                Toast.makeText(requireContext(), "Failed to load data for Phan Loai", Toast.LENGTH_SHORT).show();
            }
        });

        btnThemmoithietbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenthuonghieu = spinChonThuongHieu.getSelectedItem().toString();
                String tendonvi = spinChonDonViTinh.getSelectedItem().toString();
                String tenphanloai = spinPhanLoai.getSelectedItem().toString();
                int soLuong;
                try {
                    soLuong = Integer.parseInt(edtNhapSoLuong.getText().toString().trim());
                } catch (NumberFormatException e) {
                    Toast.makeText(requireContext(), "Vui lòng nhập số lượng (Bắt buọc): ", Toast.LENGTH_SHORT).show();
                    return;
                }
                UpdateThietBi updateThietBi = new UpdateThietBi(
                        danhSachThietBiUser.id_thiet_bi,
                        edtAddTenthietbi.getText().toString().trim(),
                        edtAddThongSo.getText().toString().trim(),
                        tenthuonghieu,
                        soLuong,
                        edtNhapMoTa.getText().toString().trim(),
                        tendonvi,
                        tenphanloai,
                        null
                );

                RetrofitInstance.getRetrofitInstance().apiInterface.update_thiet_bi(updateThietBi).enqueue(new Callback<UpdateThietBi>() {
                    @Override
                    public void onResponse(Call<UpdateThietBi> call, Response<UpdateThietBi> response) {
                        UpdateThietBi apiResponse = response.body();
                        if(response.isSuccessful()){
                            Toast.makeText(requireContext(), apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            Show_All_Data();
                        }
                        else{
                            Toast.makeText(requireContext(), apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateThietBi> call, Throwable t) {

                    }
                });
            }
        });

        btnThoatThietBi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}