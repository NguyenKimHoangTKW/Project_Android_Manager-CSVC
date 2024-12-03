package com.example.quanlycsvc.ActivityInterface.ui.account;

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
import com.example.quanlycsvc.AdapterModels.AccountAdapter;
import com.example.quanlycsvc.Api.RetrofitInstance;
import com.example.quanlycsvc.Models.Account;
import com.example.quanlycsvc.Models.DroplistRole;
import com.example.quanlycsvc.Models.PhongHoc;
import com.example.quanlycsvc.Models.UpdateAccount;
import com.example.quanlycsvc.R;
import com.example.quanlycsvc.databinding.FragmentAccountBinding;
import com.example.quanlycsvc.databinding.FragmentHomeBinding;
import com.example.quanlycsvc.databinding.FragmentPhongHocBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment {
    ListView lvShowAccount;
    ArrayList<Account> list;
    AccountAdapter adapter;

    private FragmentAccountBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Set_Control();
        Show_all_account();
        return root;
    }

    public void Set_Control(){
        lvShowAccount = binding.lvShowAccount;
        list = new ArrayList<>();
        adapter = new AccountAdapter(requireContext(),list);
        lvShowAccount.setAdapter(adapter);
        lvShowAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Account account = list.get(position);
                showDialog(account);
            }
        });
    }
    public void Show_all_account(){
        RetrofitInstance.getRetrofitInstance().apiInterface.get_full_account().enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    list.clear();
                    list.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("API Response", "Không thành công, mã lỗi: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                Log.e("API Error", "Không thể lấy dữ liệu Account: " + t.getMessage());

            }
        });
    }

    private void showDialog(Account account) {
        Spinner spinRole;
        EditText edtEditNameAccount, edtEmailAccount;
        Button btnEditAccount, btnCloseAccount;
        View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_update_account, null);
        AlertDialog dialog = new AlertDialog.Builder(requireContext()).setView(dialogView).create();
        spinRole = dialogView.findViewById(R.id.spinRole);
        edtEditNameAccount = dialogView.findViewById(R.id.edtEditNameAccount);
        edtEmailAccount = dialogView.findViewById(R.id.edtEmailAccount);
        btnEditAccount = dialogView.findViewById(R.id.btnEditAccount);
        btnCloseAccount = dialogView.findViewById(R.id.btnCloseAccount);

        RetrofitInstance.getRetrofitInstance().apiInterface.droplistRole().enqueue(new Callback<List<DroplistRole>>() {
            @Override
            public void onResponse(Call<List<DroplistRole>> call, Response<List<DroplistRole>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<DroplistRole> droplistRoleList = response.body();

                    List<String> tenroleList = new ArrayList<>();

                    for (DroplistRole tenrole : droplistRoleList) {
                        tenroleList.add(tenrole.getTen_role());
                    }

                    ArrayAdapter<String> roleAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, tenroleList);
                    roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinRole.setAdapter(roleAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<DroplistRole>> call, Throwable t) {
            }
        });

        edtEditNameAccount.setText(account.getName());
        edtEmailAccount.setText(account.getEmail());
        edtEditNameAccount.setEnabled(false);
        edtEmailAccount.setEnabled(false);
        btnEditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedrole = spinRole.getSelectedItem().toString();
                UpdateAccount updateAccount = new UpdateAccount(account.id_account,selectedrole,null);
                RetrofitInstance.getRetrofitInstance().apiInterface.update_account(updateAccount).enqueue(new Callback<UpdateAccount>() {
                    @Override
                    public void onResponse(Call<UpdateAccount> call, Response<UpdateAccount> response) {
                        UpdateAccount apiResponse = response.body();
                        Toast.makeText(getActivity(), apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        Show_all_account();
                    }

                    @Override
                    public void onFailure(Call<UpdateAccount> call, Throwable t) {
                        Log.e("API Error", "Không thể cập nhật");
                    }
                });
            }
        });

        btnCloseAccount.setOnClickListener(new View.OnClickListener() {
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