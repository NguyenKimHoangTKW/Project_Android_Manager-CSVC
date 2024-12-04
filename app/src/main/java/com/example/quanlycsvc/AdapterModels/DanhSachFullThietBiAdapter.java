package com.example.quanlycsvc.AdapterModels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlycsvc.Models.DanhSachThietBiDaMuonUser;
import com.example.quanlycsvc.Models.DanhSachThietBiUser;
import com.example.quanlycsvc.R;

import java.util.ArrayList;

public class DanhSachFullThietBiAdapter extends BaseAdapter {
    Context context;
    ArrayList<DanhSachThietBiUser> list;

    public DanhSachFullThietBiAdapter(Context context, ArrayList<DanhSachThietBiUser> list) {
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
        TextView tvrowtenthietbi, tvrowthongso, tvrowthuonghieu, tvrowsoluong, tvrowphanloai, tvrowtrangthai,tvrowDonvitinh;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.danh_sach_full_thiet_bi_admin_row, null);

        tvrowtenthietbi = row.findViewById(R.id.tvrowtenthietbia);
        tvrowthongso = row.findViewById(R.id.tvrowthongsoa);
        tvrowthuonghieu = row.findViewById(R.id.tvrowthuonghieua);
        tvrowsoluong = row.findViewById(R.id.tvrowsoluonga);
        tvrowphanloai = row.findViewById(R.id.tvrowphanloaia);
        tvrowtrangthai = row.findViewById(R.id.tvrowtrangthaia);
        tvrowDonvitinh = row.findViewById(R.id.tvrowDonvitinha);
        DanhSachThietBiUser thietBiUser = list.get(position);

        tvrowtenthietbi.setText(thietBiUser.ten_thiet_bi);
        tvrowthongso.setText("Thông số: " + thietBiUser.thong_so);
        tvrowthuonghieu.setText("Thương hiệu: " + thietBiUser.ten_thuong_hieu);
        tvrowsoluong.setText("Số lượng còn: " + String.valueOf(thietBiUser.so_luong));
        tvrowphanloai.setText("Loại: "+ thietBiUser.ten_phan_loai);
        tvrowtrangthai.setText("Trạng thái: "+ thietBiUser.ten_trang_thaii);
        tvrowDonvitinh.setText("Đơn vị tính: "+ thietBiUser.ten_don_vi_tinh);
        return row;
    }
}
