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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DanhSachDaMuonAdapter extends BaseAdapter {
    Context context;
    ArrayList<DanhSachThietBiDaMuonUser> list;

    public DanhSachDaMuonAdapter(Context context, ArrayList<DanhSachThietBiDaMuonUser> list) {
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
        TextView tvShowtenthietbimuon, tvShowtenphonghocmuon, tvSoluongmuon, tvYeuCauMuon, tvLydohuymuonuser, tvngaydangkymuon, tvNgayhuyuser,tvNgaymuonuser, tvNgaytrauser, tvTrangthaimuon;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.danh_sach_thiet_bi_da_muon_row, null);

        tvShowtenthietbimuon = row.findViewById(R.id.tvShowtenthietbimuon);
        tvShowtenphonghocmuon = row.findViewById(R.id.tvShowtenphonghocmuon);
        tvSoluongmuon = row.findViewById(R.id.tvSoluongmuon);
        tvYeuCauMuon = row.findViewById(R.id.tvYeuCauMuon);
        tvLydohuymuonuser = row.findViewById(R.id.tvLydohuymuonuser);
        tvngaydangkymuon = row.findViewById(R.id.tvngaydangkymuon);
        tvNgaymuonuser = row.findViewById(R.id.tvNgaymuonuser);
        tvNgaytrauser = row.findViewById(R.id.tvNgaytrauser);
        tvTrangthaimuon = row.findViewById(R.id.tvTrangthaimuon);
        tvNgayhuyuser = row.findViewById(R.id.tvNgayhuyuser);
        DanhSachThietBiDaMuonUser thietBiDaMuonUser = list.get(position);

        tvShowtenthietbimuon.setText("Tên thiết bị mượn: " + thietBiDaMuonUser.ten_thiet_bi);
        tvShowtenphonghocmuon.setText("Phòng học mượn: " + thietBiDaMuonUser.ten_phong_hoc);
        tvSoluongmuon.setText("Số lượng mượn: " + thietBiDaMuonUser.so_luong_muon);
        tvYeuCauMuon.setText("Yêu cầu từ bạn: " + thietBiDaMuonUser.yeu_cau);
        tvLydohuymuonuser.setText(thietBiDaMuonUser.ly_do_huy != null ? "Thông báo từ quản lý: " + thietBiDaMuonUser.ly_do_huy : "");

        tvngaydangkymuon.setText("Ngày đăng ký mượn: " + formatTimestamp(thietBiDaMuonUser.ngay_dang_ky_muon));
        tvNgaymuonuser.setText(thietBiDaMuonUser.ngay_muon != null ? "Ngày duyệt mượn: " + formatTimestamp(thietBiDaMuonUser.ngay_muon) : "");
        tvNgaytrauser.setText(thietBiDaMuonUser.ngay_tra != null ? "Ngày bạn trả: " + formatTimestamp(thietBiDaMuonUser.ngay_tra) : "");
        tvNgayhuyuser.setText(thietBiDaMuonUser.ngay_huy != null ? "Ngày hủy: "+ formatTimestamp(thietBiDaMuonUser.ngay_huy) : "");
        tvTrangthaimuon.setText(thietBiDaMuonUser.ten_trang_thaii);

        return row;
    }

    private String formatTimestamp(Integer timestamp) {
        if (timestamp == null) return "";
        long timeInMillis = timestamp * 1000L;
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMM yyyy HH:mm", Locale.getDefault());
        return sdf.format(new Date(timeInMillis));
    }

}
