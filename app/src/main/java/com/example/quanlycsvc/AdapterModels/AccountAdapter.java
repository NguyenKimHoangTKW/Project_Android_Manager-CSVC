package com.example.quanlycsvc.AdapterModels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlycsvc.Models.Account;
import com.example.quanlycsvc.Models.DanhSachThietBiDaMuonUser;
import com.example.quanlycsvc.R;

import java.util.ArrayList;

public class AccountAdapter extends BaseAdapter {
    Context context;
    ArrayList<Account> list;

    public AccountAdapter(Context context, ArrayList<Account> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tvNameAccount,tvEmailAccount,tvRoleAccount;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.account_row, null);
        tvNameAccount = row.findViewById(R.id.tvNameAccount);
        tvEmailAccount = row.findViewById(R.id.tvEmailAccount);
        tvRoleAccount = row.findViewById(R.id.tvRoleAccount);
        Account account = list.get(position);
        tvNameAccount.setText("Tên Account: "+ account.name);
        tvEmailAccount.setText("Email: "+ account.email);
        tvRoleAccount.setText("Quyền hạn: "+ account.ten_role);

        return row;
    }
}
