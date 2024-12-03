package com.example.quanlycsvc.ActivityInterface.ui.phonghoc;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.quanlycsvc.AdapterModels.PhongHocAdapter;
import com.example.quanlycsvc.Api.RetrofitInstance;
import com.example.quanlycsvc.Models.PhongHoc;
import com.example.quanlycsvc.R;
import com.example.quanlycsvc.databinding.FragmentPhongHocBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhongHocFragment extends Fragment {
    ListView lvADPhonghoc;
    ArrayList<PhongHoc> list, listPhongHocFiltered;
    PhongHocAdapter adapter;
    Button btnaddnewphonghoc;
    EditText edttenphonghoc;
    private FragmentPhongHocBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentPhongHocBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Set_control();
        readdata();

        lvADPhonghoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhongHoc selected = listPhongHocFiltered.get(position);
                showDialog(selected);
            }
        });

        edttenphonghoc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterPhongHoc(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        btnaddnewphonghoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create_phong_hoc();
            }
        });

        return root;
    }

    public void Set_control() {
        lvADPhonghoc = binding.lvADPhonghoc;
        edttenphonghoc = binding.edttenphonghoc;
        btnaddnewphonghoc = binding.btnaddnewphonghoc;

        list = new ArrayList<>();
        listPhongHocFiltered = new ArrayList<>();
        adapter = new PhongHocAdapter(requireContext(), listPhongHocFiltered);
        lvADPhonghoc.setAdapter(adapter);
    }

    private void readdata() {
        RetrofitInstance.getRetrofitInstance().apiInterface.getfullphonghoc().enqueue(new Callback<List<PhongHoc>>() {
            @Override
            public void onResponse(Call<List<PhongHoc>> call, Response<List<PhongHoc>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    list.clear();
                    list.addAll(response.body());
                    listPhongHocFiltered.clear();
                    listPhongHocFiltered.addAll(list);
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("API Response", "Không thành công, mã lỗi: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<PhongHoc>> call, Throwable t) {
                Log.e("API Error", "Không thể lấy dữ liệu phòng học: " + t.getMessage());
            }
        });
    }

    private void create_phong_hoc() {
        String tenPhongHoc = edttenphonghoc.getText().toString().trim();
        if (tenPhongHoc.isEmpty()) {
            Toast.makeText(getActivity(), "Tên phòng học không được để trống!", Toast.LENGTH_SHORT).show();
            return;
        }

        PhongHoc createphonghoc = new PhongHoc(0, tenPhongHoc, null);
        RetrofitInstance.getRetrofitInstance().apiInterface.create_phong_hoc(createphonghoc).enqueue(new Callback<PhongHoc>() {
            @Override
            public void onResponse(Call<PhongHoc> call, Response<PhongHoc> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    readdata();
                    edttenphonghoc.setText("");
                } else {
                    Log.e("API Error", "Không thể thêm phòng học: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<PhongHoc> call, Throwable t) {
                Log.e("API Error", "Không thể thêm phòng học: " + t.getMessage());
            }
        });
    }

    private void showDialog(PhongHoc phongHoc) {
        View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_admin_phong_hoc, null);
        EditText edtTenPhongHoc = dialogView.findViewById(R.id.edtTenPhongHoc);
        Button btnSuaPhongHoc = dialogView.findViewById(R.id.btnSuaPhongHoc);
        Button btnXoaPhongHoc = dialogView.findViewById(R.id.btnXoaPhongHoc);
        Button btnTroVe = dialogView.findViewById(R.id.btnTroVe);

        edtTenPhongHoc.setText(phongHoc.getTen_phong_hoc());

        AlertDialog dialog = new AlertDialog.Builder(requireContext()).setView(dialogView).create();

        btnSuaPhongHoc.setOnClickListener(v -> {
            String tenMoi = edtTenPhongHoc.getText().toString().trim();
            if (tenMoi.isEmpty()) {
                Toast.makeText(getActivity(), "Tên phòng học không được để trống!", Toast.LENGTH_SHORT).show();
                return;
            }

            PhongHoc updatephonghoc = new PhongHoc(phongHoc.getId_phong_hoc(), tenMoi, null);
            RetrofitInstance.getRetrofitInstance().apiInterface.update_phong_hoc(updatephonghoc).enqueue(new Callback<PhongHoc>() {
                @Override
                public void onResponse(Call<PhongHoc> call, Response<PhongHoc> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        readdata();
                        dialog.dismiss();
                    } else {
                        Log.e("API Error", "Không thể cập nhật phòng học: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<PhongHoc> call, Throwable t) {
                    Log.e("API Error", "Không thể cập nhật phòng học: " + t.getMessage());
                }
            });
        });

        btnXoaPhongHoc.setOnClickListener(v -> {
            RetrofitInstance.getRetrofitInstance().apiInterface.delete_phong_hoc(phongHoc).enqueue(new Callback<PhongHoc>() {
                @Override
                public void onResponse(Call<PhongHoc> call, Response<PhongHoc> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        readdata();
                        dialog.dismiss();
                    } else {
                        Log.e("API Error", "Không thể xóa phòng học: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<PhongHoc> call, Throwable t) {
                    Log.e("API Error", "Không thể xóa phòng học: " + t.getMessage());
                }
            });
        });

        btnTroVe.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    public void filterPhongHoc(String query) {
        listPhongHocFiltered.clear();
        if (query.isEmpty()) {
            listPhongHocFiltered.addAll(list);
        } else {
            for (PhongHoc phongHoc : list) {
                if (phongHoc.getTen_phong_hoc() != null &&
                        phongHoc.getTen_phong_hoc().toLowerCase().contains(query.toLowerCase())) {
                    listPhongHocFiltered.add(phongHoc);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
