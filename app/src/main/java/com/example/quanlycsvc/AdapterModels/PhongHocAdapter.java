package com.example.quanlycsvc.AdapterModels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlycsvc.Models.PhongHoc;
import com.example.quanlycsvc.R;

import java.util.ArrayList;

public class PhongHocAdapter extends BaseAdapter {
    Context context;
    ArrayList<PhongHoc> list;

    public PhongHocAdapter(Context context, ArrayList<PhongHoc> list) {
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.phong_hoc_row,null);
        TextView tvADShowPH = (TextView) row.findViewById(R.id.tvADShowPH);

        PhongHoc phonghoc = list.get(position);
        tvADShowPH.setText(phonghoc.ten_phong_hoc);
        return row;
    }
}
